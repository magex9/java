package ca.magex.tomcat.embedded;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class EmbeddedTomcatExample {

	public static void main(String[] args) throws Exception {

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("target/embedded-tomcat");
		tomcat.setHostname("localhost");
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/app",
				new File("/src/main/webapp").getAbsolutePath());
		
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(
				new DirResourceSet(resources, 
						"/WEB-INF/classes", 
						new File("target/classes").getAbsolutePath(), 
						"/"));
		ctx.setResources(resources);

		Tomcat.addServlet(ctx, SampleAuthenticationServlet.class.getName(), 
				new SampleAuthenticationServlet());
		ctx.addServletMapping("/data/*", SampleAuthenticationServlet.class.getName());

		tomcat.start();
		tomcat.getServer().await();

		// go to: http://localhost:8080/app/data/test

	}
}