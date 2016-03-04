package ca.magex.tomcat.embedded;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.MemoryRealm;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

public class EmbeddedTomcatExample {

	public static void main(String[] args) throws Exception {
		String basedir = new File(".").getAbsolutePath();
		String roles = basedir + "/src/main/resources/tomcat-users.xml";

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("target/embedded-tomcat");
		tomcat.setHostname("localhost");
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/sample",
				new File(basedir + "/src/main/webapp").getAbsolutePath());
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
		ctx.setResources(resources);

		Tomcat.addServlet(ctx, "SampleServlet", new SampleServlet());
		ctx.addServletMapping("/data/*", "SampleServlet");

		LoginConfig config = new LoginConfig();
		config.setAuthMethod("FORM");
		config.setLoginPage("/login.jsp");
		ctx.setLoginConfig(config);
		ctx.addSecurityRole("admin");
		SecurityConstraint constraint = new SecurityConstraint();
		constraint.addAuthRole("admin");
		SecurityCollection collection = new SecurityCollection();
		collection.addPattern("/*");
		constraint.addCollection(collection);
		ctx.addConstraint(constraint);

		MemoryRealm realm = new MemoryRealm();
		realm.setPathname(roles);
		tomcat.getEngine().setRealm(realm);

		tomcat.start();
		tomcat.getServer().await();

		// go to: http://localhost:8080/sample/data/test

	}
}