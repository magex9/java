package ca.magex.jena.rdf.readers;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

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

public class StreamingReader {

	public static void main(String[] args) throws Exception {
		Model model = ModelFactory.createDefaultModel();
		
		Property name = createProperty(model, "common.topic.name");
		Property alertType = createProperty(model, "message.alert.type");
		Property jobConnection = createProperty(model, "scheduler.job.connection");
		Property taskJob = createProperty(model, "scheduler.task.job");
		Property taskCron = createProperty(model, "scheduler.task.cron");
		Property task = createProperty(model, "scheduler.event.task");
		Property startTime = createProperty(model, "scheduler.event.startTime");
		Property endTime = createProperty(model, "scheduler.event.endTime");
		Property eventSuccess = createProperty(model, "scheduler.event.success");
		Property eventResult = createProperty(model, "scheduler.event.result");
				
		Resource success = createResource(model, "message", "alert", "success")
				.addProperty(name, "Success")
				.addProperty(alertType, "success");

		Resource error = createResource(model, "message", "alert", "error")
				.addProperty(name, "Failure")
				.addProperty(alertType, "error");

		Resource warning = createResource(model, "message", "alert", "warn")
				.addProperty(name, "Warning")
				.addProperty(alertType, "warn");

		StringBuilder statement = new StringBuilder();
		statement.append("select distinct ?topic ?prop ?value ");
		statement.append("where { ");
		statement.append("?topic ?prop ?value . ");
		statement.append("} ");
		
		Resource job1 = createResource(model, "scheduler", "job", "Refresh App")
				.addProperty(name, "Refresh App")
				.addProperty(jobConnection, "http://app/refresh");

		Resource task1 = createResource(model, "scheduler", "task", job1.getURI() + ":Refresh app every day")
				.addProperty(name, "Refresh app every day")
				.addProperty(taskJob, job1)
				.addProperty(taskCron, "* * * * *");

		Calendar cal = Calendar.getInstance();
		cal.set(2016, 1, 1, 0, 0, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYmmddHHMMss");
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {

			String startTimeValue = sdf.format(cal.getTime());
			cal.add(Calendar.MINUTE, 5);
			String endTimeValue = sdf.format(cal.getTime());
			cal.add(Calendar.HOUR, 1);
			cal.set(Calendar.MINUTE, 0);
			
			Resource successValue = rand.nextInt(10) < 8 ? success : error;
			
			createResource(model, "scheduler", "event", task1.getURI() + ":" + startTimeValue)
					.addProperty(task, task1)
					.addProperty(startTime, startTimeValue)
					.addProperty(endTime, endTimeValue)
					.addProperty(eventSuccess, successValue)
					.addProperty(eventResult, Integer.toString(rand.nextInt()));
		}
		
		//print(model, statement.toString());

		System.out.println("Model built");
		
		StringBuilder query = new StringBuilder();
		query.append("select distinct ?eventRef ?taskName ?jobName ?cron ?startTime ?endTime ?success ?result ");
		query.append("where {");
		query.append("?taskRef prop:scheduler.task.job ?jobRef .");
		query.append("?taskRef prop:common.topic.name ?taskName .");
		query.append("?jobRef prop:common.topic.name ?jobName .");
		query.append("?taskRef prop:scheduler.task.cron ?cron .");
		query.append("?eventRef prop:scheduler.event.task ?taskRef . ");
		query.append("?eventRef prop:scheduler.event.startTime ?startTime . ");
		query.append("OPTIONAL { ?eventRef prop:scheduler.event.endTime ?endTime } . ");
		query.append("OPTIONAL { ?eventRef prop:scheduler.event.result ?result } . ");
		query.append("?eventRef prop:scheduler.event.success ?successRef . ");
		query.append("?successRef prop:common.topic.name ?success . ");
		query.append("?successRef prop:message.alert.type 'error' . ");
		query.append("} order by desc(?eventRef) limit 10 offset 0");
		System.out.println(query.toString());
		
		print(model, query.toString());

		model.write(new FileOutputStream(new File("target/scheduler.ttl")), "N-TRIPLE");

	}
	
	private static Property createProperty(Model model, String property) {
		return model.createProperty("http://magex.ca/data/property/" + property);
	}

	public static Resource createResource(Model model, String domain, String type, String key) {
		return model.createResource("http://magex.ca/data/entity/" + EntityRef.buildKey(new Type(new Domain(domain), type), key));
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
