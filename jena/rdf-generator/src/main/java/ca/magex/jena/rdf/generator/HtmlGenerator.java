package ca.magex.jena.rdf.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class HtmlGenerator {

	public static void main(String[] args) throws Exception {
		File file = new File("target/architecture.ttl");
		HtmlGenerator.html(HtmlGenerator.buildModel(file), "target/html");
	}
	
	public static void print(Model model, String statement) {
		StringBuilder sb = new StringBuilder();
		sb.append("PREFIX entity: <http://magex.ca/data/system/entity/>");
		sb.append("PREFIX prop: <http://magex.ca/data/system/property/>");
		sb.append(statement);
		Query query = QueryFactory.create(sb.toString()); 
		QueryExecution exec = QueryExecutionFactory.create(query, model);
		ResultSet results = exec.execSelect();
		ResultSetFormatter.out(System.out, results, query);
	}
	
	public static void html(Model model, String outputDir) throws Exception {
		StringBuilder statement = new StringBuilder();
		statement.append("select distinct ?topic ");
		statement.append("where {");
		statement.append("?topic ?prop ?value .");
		statement.append("}");
		
		List<Map<String, String>> results = query(model, statement);
		for (Map<String, String> row : results) {
			FormattedStringBuilder sb = new FormattedStringBuilder();
			appendTopic(model, row.get("topic"), sb);
			File file = new File("target/html/" + row.get("topic") + ".html");
			FileUtils.writeStringToFile(file, sb.toString());
			System.out.println("Created: " + file.getAbsolutePath());
		}
		
	}
	
	public static void appendTopic(Model model, String topicRef, FormattedStringBuilder sb) throws Exception {
		sb.append("Topic: " + topicRef);
		sb.append("  Outgoing: ");
		appendOutgoing(model, topicRef, sb);
		sb.append("  Incoming: ");
		appendIncoming(model, topicRef, sb);
	}
	
	public static void appendOutgoing(Model model, String ref, FormattedStringBuilder sb) {
		StringBuilder statement = new StringBuilder();
		statement.append("select distinct ?prop ?value ?name ");
		statement.append("where {");
		statement.append("entity:" + ref + " ?prop ?value .");
		statement.append("OPTIONAL { ?value prop:common.topic.name ?name } .");
		statement.append("} order by ?prop");

		List<Map<String, String>> results = query(model, statement);
		sb.append("<dl>");
		for (Map<String, String> row : results) {
			sb.append("<dt>" + row.get("prop") + "</dt>");
			sb.append("<dd>" + row.get("value") + "</dd>");
			if (row.get("name") != null) {
				sb.append("<dd><a href=\"" + row.get("value") + ".html\">" + row.get("name") + "</a></dd>");
			}
		}
		sb.append("</dl>");
	}
	
	public static void appendIncoming(Model model, String ref, FormattedStringBuilder sb) {
		StringBuilder statement = new StringBuilder();
		statement.append("select distinct ?prop ?value ?name ");
		statement.append("where {");
		statement.append("?value ?prop entity:" + ref + " .");
		statement.append("OPTIONAL { ?value prop:common.topic.name ?name } .");
		statement.append("} order by ?value");

		List<Map<String, String>> results = query(model, statement);
		sb.append("<dl>");
		for (Map<String, String> row : results) {
			sb.append("<dt>" + row.get("prop") + "</dt>");
			if (row.get("name") != null) {
				sb.append("<dd><a href=\"" + row.get("value") + ".html\">" + row.get("name") + "</a></dd>");
			}
		}
		sb.append("</dl>");
	}
	
	public static List<Map<String, String>> query(Model model, StringBuilder statement) {
		return query(model, statement.toString());
	}
	
	public static List<Map<String, String>> query(Model model, String statement) {
		List<Map<String, String>> results = new ArrayList<Map<String,String>>();
		FormattedStringBuilder sb = new FormattedStringBuilder();
		sb.append("PREFIX entity: <http://magex.ca/data/system/entity/>");
		sb.append("PREFIX prop: <http://magex.ca/data/system/property/>");
		sb.append(statement);
		//System.out.println(sb.toString());
		Query query = QueryFactory.create(sb.toString()); 
		QueryExecution exec = QueryExecutionFactory.create(query, model);
		ResultSet rs = exec.execSelect();
		while (rs.hasNext()) {
			QuerySolution result = rs.next();
			Map<String, String> data = new HashMap<String, String>();
			Iterator<String> iter = result.varNames();
			while (iter.hasNext()) {
				String name = iter.next();
				RDFNode value = result.get(name);
				if (value != null)
					data.put(name,  value.toString()
							.replaceAll("http://magex.ca/data/system/property/", "")
							.replaceAll("http://magex.ca/data/system/entity/", ""));
			}
			results.add(data);
		}
		return results;
	}
	
	public static Model buildModel(File... input) {
		try {
			Model model = ModelFactory.createDefaultModel();
			for (File file : input) {
				InputStream is;
					is = new FileInputStream(file);
				model.read(is, null, "TTL");
			}
			return model;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
}
