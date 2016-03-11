package ca.magex.jena.rdf.readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import ca.magex.jena.rdf.model.Domain;
import ca.magex.jena.rdf.model.EntityRef;
import ca.magex.jena.rdf.model.Type;

public class Dups {

	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();
		
		Property name = model.createProperty("http://magex.ca/name");
		Property link = model.createProperty("http://magex.ca/link");

		StringBuilder statement = new StringBuilder();
		statement.append("select distinct ?topic ?prop ?value ");
		statement.append("where {");
		statement.append("?topic ?prop ?value .");
		statement.append("}");
		
		Resource a = model.createResource("a")
				.addProperty(name, "A");

		Resource b = model.createResource("b")
				.addProperty(name, "b")
				.addProperty(link, "a");

		print(model, statement.toString());

		StringBuilder query = new StringBuilder();
		query.append("select distinct ?a ?b ?n ");
		query.append("where {");
		query.append("?a <http://magex.ca/link> ?b .");
		query.append("?a <http://magex.ca/name> ?n .");
		query.append("}");
		
		System.out.println(query.toString());
		
		print(model, query.toString());


	}
	
	public static void print(Model model, String statement) {
		StringBuilder sb = new StringBuilder();
		sb.append("PREFIX entity: <http://magex.ca/data/entity/>");
		sb.append("PREFIX prop: <http://magex.ca/data/property/>");
		sb.append(statement);
		Query query = QueryFactory.create(sb.toString()); 
		QueryExecution exec = QueryExecutionFactory.create(query, model);
		ResultSet results = exec.execSelect();
		ResultSetFormatter.out(System.out, results, query);
	}

	
}
