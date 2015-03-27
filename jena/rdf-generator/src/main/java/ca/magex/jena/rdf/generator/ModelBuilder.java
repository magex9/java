package ca.magex.jena.rdf.generator;

import java.util.HashMap;
import java.util.Map;

import ca.magex.jena.rdf.model.Domain;
import ca.magex.jena.rdf.model.EntityRef;
import ca.magex.jena.rdf.model.Model;
import ca.magex.jena.rdf.model.Predicate;
import ca.magex.jena.rdf.model.Property;
import ca.magex.jena.rdf.model.Subject;
import ca.magex.jena.rdf.model.Triple;
import ca.magex.jena.rdf.model.Type;
import ca.magex.jena.rdf.model.Value;

public class ModelBuilder {

	private Map<String, Object> variables;

	private Model model;

	public ModelBuilder() {
		this.variables = new HashMap<String, Object>();
		this.model = new Model();
	}
	
	public Model getModel() {
		return model;
	}

	public void query(String string) {

	}

	public void set(String key, Object value) {
		variables.put(key, value);
	}
	
	public void put(Object subject, Object predicate, Object value) {
		model.getTriples().add(
				new Triple((Subject) subject, (Predicate) predicate,
						(Value) value));
	}
	
	public void put(Subject subject, Predicate predicate, Value value) {
		model.getTriples().add(new Triple(subject, predicate, value));
	}

	public static EntityRef topic(String domainName, String typeName,
			String value) {
		Domain domain = new Domain(domainName);
		Type type = new Type(domain, typeName);
		return new EntityRef(type, value);
	}

	public static Property prop(String domainName, String typeName, String name) {
		Domain domain = new Domain(domainName);
		Type type = new Type(domain, typeName);
		return new Property(type, name);
	}

}
