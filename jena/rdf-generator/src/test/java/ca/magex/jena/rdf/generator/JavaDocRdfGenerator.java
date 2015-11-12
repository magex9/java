package ca.magex.jena.rdf.generator;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;

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
			String classKey = classDoc.toString();
			EntityRef classRef = ref("javadoc", "class", classKey);
			emit(classRef, "javadoc/class/name", classDoc.toString());
			emit(classRef, "javadoc/class/description", classDoc.commentText());
			for (MethodDoc methodDoc : classDoc.methods()) {
				String methodKey = classKey + "." + methodDoc.name() + methodDoc.signature();
				EntityRef methodRef = ref("javadoc", "method", methodKey);
				emit(methodRef, "javadoc/method/name", methodDoc.name());
				emit(methodRef, "javadoc/method/description", methodDoc.commentText());
				emit(methodRef, "javadoc/method/modifiers", methodDoc.modifiers());
				emit(methodRef, "javadoc/method/return", methodDoc.returnType().qualifiedTypeName());
				emit(methodRef, "javadoc/method/signature", methodDoc.signature());
				emit(classRef, "javadoc/class/method", methodRef);
				int p = 0;
				for (Parameter parameterDoc : methodDoc.parameters()) {
					String parameterKey = methodKey + "." + parameterDoc.name();
					EntityRef parameterRef = ref("javadoc", "parameter", parameterKey);
					emit(parameterRef, "javadoc/parameter/name", parameterDoc.name());
					emit(parameterRef, "javadoc/parameter/type", parameterDoc.type().qualifiedTypeName());
					emit(parameterRef, "javadoc/parameter/index", Integer.toString(p++));
					emit(methodRef, "javadoc/method/parameter", parameterRef);
				}
				for (ParamTag paramTag : methodDoc.paramTags()) {
					String paramTagKey = methodKey + "." + paramTag.parameterName();
					EntityRef paramTagRef = ref("javadoc", "paramTag", paramTagKey);
					emit(paramTagRef, "javadoc/annotation/name", paramTag.parameterName());
					emit(paramTagRef, "javadoc/annotation/comment", paramTag.parameterComment());
					emit(methodRef, "javadoc/method/paramTag", paramTagRef);
				}
				for (Tag tag : methodDoc.tags()) {
					String tagKey = methodKey + "." + tag.name();
					EntityRef tagRef = ref("javadoc", "tag", tagKey);
					emit(tagRef, "javadoc/annotation/name", tag.name());
					emit(tagRef, "javadoc/annotation/comment", tag.text());
					emit(methodRef, "javadoc/method/tag", tagRef);
				}
				for (AnnotationDesc annotationDesc : methodDoc.annotations()) {
					String annotationKey = methodKey + "." + annotationDesc.toString();
					EntityRef annotationRef = ref("javadoc", "annotation", annotationKey);
					emit(annotationRef, "javadoc/annotation/values", annotationDesc.elementValues().toString());
					emit(methodRef, "javadoc/method/parameter", annotationRef);
				}
			}
		}
	}

	private EntityRef ref(String domain, String type, String key) throws IOException {
		EntityRef ref = new EntityRef(domain, type, "ref://" + domain + "/" + type + "/" + key);
		emit(ref, "common/topic/key", "ref://" + domain + "/" + type + "/" + key);
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
