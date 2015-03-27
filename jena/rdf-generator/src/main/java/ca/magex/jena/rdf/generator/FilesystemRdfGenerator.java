package ca.magex.jena.rdf.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.digest.DigestUtils;

import ca.magex.jena.rdf.model.EntityRef;
import ca.magex.jena.rdf.model.Predicate;
import ca.magex.jena.rdf.model.Property;
import ca.magex.jena.rdf.model.Subject;
import ca.magex.jena.rdf.model.TextValue;
import ca.magex.jena.rdf.model.Triple;
import ca.magex.jena.rdf.model.Type;
import ca.magex.jena.rdf.model.Value;

public class FilesystemRdfGenerator {

	private File file;
	
	private String basedir;
	
	private OutputStream gos;
	
	public void generate(File input, File output) throws Exception {
		basedir = input.getAbsolutePath();
		file = output;
		file.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(file);
		gos = new GZIPOutputStream(fos);
		processDir(input);
		gos.close();
		fos.close();
	}
	
	public void processDir(File dir) throws Exception {
		for (File child : dir.listFiles()) {
			if (child.isDirectory()) {
				processDir(child, dir);
			} else {
				processFile(child, dir);
			}
		}
	}

	private void processDir(File dir, File parent) throws Exception {
		if (!isIntrestedIn(dir))
			return;
		Subject subject = new EntityRef("filesystem", "directory", getPath(dir));
		emit(subject, "filesystem/directory/name", dir.getName());
		emit(subject, "filesystem/directory/full", getPath(dir));
		emit(subject, "filesystem/directory/parent", new EntityRef("filesystem", "directory", getPath(parent)));
		processDir(dir);
	}

	private void processFile(File file, File parent) throws Exception {
		if (!isIntrestedIn(file))
			return;
		Subject subject = new EntityRef("filesystem", "file", file.getAbsolutePath());
		try {
			emit(subject, "filesystem/file/md5", md5(file));
			emit(subject, "filesystem/file/name", file.getName());
			emit(subject, "filesystem/file/type", file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase());
			emit(subject, "filesystem/file/full", getPath(file));
			emit(subject, "filesystem/file/dir", new EntityRef(new Type("filesystem", "directory"), getPath(parent)));
		} catch (Exception e) {
			System.out.println("Skipping: " +  getPath(file));
		}
	}
	
	private String getPath(File file) {
		return file.getAbsolutePath().replace(basedir, "");
	}
	
	private boolean isIntrestedIn(File file) {
		if (file.getName().startsWith("."))
			return false;
		if (file.getName().endsWith(".ini"))
			return false;
		return true;
	}
	
	private void emit(Subject subject, String predicate, String value) throws IOException {
		emit(subject, predicate, new TextValue(value));
	}
	
	private void emit(Subject subject, String predicate, Value value) throws IOException {
		String[] parts = predicate.split("/");
		emit(subject, new Property(parts[0], parts[1], parts[2]), value);
	}
	
	private void emit(Subject subject, Predicate predicate, Value value) throws IOException {
		Triple triple = new Triple(subject, predicate, value);
		gos.write(triple.toString().getBytes());
		gos.write("\n".getBytes());
	}
	
	private String md5(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		String md5 = DigestUtils.md5Hex(fis);
		fis.close();
		return md5;
	}
	
}

