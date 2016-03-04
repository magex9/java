package ca.magex.tomcat.embedded;

import java.io.File;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class ServletOnlyTomcat {

	public static void main(String[] args) throws Exception {

		String basedir = "target/embedded-server";

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(basedir);
		tomcat.setHostname("0.0.0.0");
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", 
				new File(basedir).getAbsolutePath());

		Tomcat.addServlet(ctx, "SampleServlet", new SampleServlet());
		ctx.addServletMapping("/*", "SampleServlet");

		tomcat.start();
		tomcat.getServer().await();

		// go to: http://localhost:8080/sample/data/test

	}
}