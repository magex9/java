package ca.magex.jena.rdf.generator;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;

import ca.magex.jena.rdf.model.EntityRef;
import ca.magex.jena.rdf.model.Predicate;
import ca.magex.jena.rdf.model.Property;
import ca.magex.jena.rdf.model.Subject;
import ca.magex.jena.rdf.model.TextValue;
import ca.magex.jena.rdf.model.Triple;
import ca.magex.jena.rdf.model.Value;

public class JavaDocRdfGenerator {

	private OutputStream os;
	
	private RootDoc rootDoc;
	
	public static void main(String[] args) {
		String src = new File("src/main/java").getAbsolutePath();
		process(src, "ca.magex.jena.rdf.generator");
	}
	
	public static void process(String src, String packageName) {
		System.out.println("Source dir: " + src);
		String doclet = JavaDocRdfGenerator.class.getName();
		com.sun.tools.javadoc.Main.execute(new String[] { 
			"-doclet", doclet,
			"-docletpath", src,
			"-sourcepath", src, packageName
		});
	}
	
	public JavaDocRdfGenerator(RootDoc rootDoc) throws IOException {
		this.rootDoc = rootDoc;
		this.os = System.out;
	}
	
	public static boolean start(RootDoc rootDoc) {
		try {
			new JavaDocRdfGenerator(rootDoc).processClasses();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void processClasses() throws IOException {
		ClassDoc[] classes = rootDoc.classes();
		for (int i = 0; i < classes.length; ++i) {
			ClassDoc classDoc = classes[i];
			EntityRef classRef = ref("javadoc", "class", classDoc.toString());
			emit(classRef, "javadoc/class/name", classDoc.toString());
			emit(classRef, "javadoc/class/description", classDoc.commentText());
			for (MethodDoc methodDoc : classDoc.methods()) {
				EntityRef methodRef = ref("javadoc", "method", classDoc.toString() + "." + methodDoc.name() + methodDoc.signature());
				emit(methodRef, "javadoc/method/name", methodDoc.name());
				emit(methodRef, "javadoc/method/description", methodDoc.commentText());
				emit(methodRef, "javadoc/method/modifiers", methodDoc.modifiers());
				emit(methodRef, "javadoc/method/signature", methodDoc.signature());
				emit(classRef, "javadoc/class/method", methodRef);
				int p = 0;
				for (Parameter parameterDoc : methodDoc.parameters()) {
					EntityRef parameterRef = ref("javadoc", "parameter", classDoc.toString());
					emit(parameterRef, "javadoc/parameter/name", parameterDoc.name());
					emit(parameterRef, "javadoc/parameter/description", parameterDoc.type().qualifiedTypeName());
					emit(parameterRef, "javadoc/parameter/index", Integer.toString(p++));
					emit(methodRef, "javadoc/method/parameter", parameterRef);
				}
			}
		}
	}

	private EntityRef ref(String domain, String type, String key) throws IOException {
		EntityRef ref = new EntityRef(domain, type, key);
		emit(ref, "common/topic/key", key);
		return ref;
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
		os.write(triple.toString().getBytes());
		os.write("\n".getBytes());
	}
	
}
