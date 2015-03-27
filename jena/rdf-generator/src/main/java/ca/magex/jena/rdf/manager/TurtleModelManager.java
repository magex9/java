package ca.magex.jena.rdf.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ca.magex.jena.rdf.model.Model;
import ca.magex.jena.rdf.model.Triple;

public class TurtleModelManager implements ModelManager {

	public void read(Model model, File file) throws IOException {
		read(model, new FileInputStream(file));
	}
	
	public void write(Model model, File file) throws IOException {
		write(model, new FileOutputStream(file));
	}
	
	@Override
	public void read(Model model, InputStream is) throws IOException {
		throw new IOException("Reading from file not implemented yet");
	}

	@Override
	public void write(Model model, OutputStream os) throws IOException {
		for (Triple triple : model.getTriples()) {
			os.write(triple.toString().getBytes());
		}
	}
	
}
