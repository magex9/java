package ca.magex.tomcat.embedded;

import java.io.File;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.MemoryRealm;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import ca.magex.web.servlet.SampleAuthenticationServlet;

public class SecureTomcatExample {

	public static void main(String[] args) throws Exception {

		String basedir = "target/secure-server";

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(basedir);
		tomcat.setHostname("0.0.0.0");
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", 
				new File(basedir).getAbsolutePath());

		Tomcat.addServlet(ctx, SampleAuthenticationServlet.class.getName(), 
				new SampleAuthenticationServlet());
		ctx.addServletMapping("/*", SampleAuthenticationServlet.class.getName());

		LoginConfig config = new LoginConfig();
		config.setAuthMethod("FORM");
		config.setLoginPage("/login.html");
		config.setErrorPage("/error.html");
		ctx.setLoginConfig(config);
		ctx.addSecurityRole("admin");
		SecurityConstraint constraint = new SecurityConstraint();
		constraint.addAuthRole("admin");
		SecurityCollection collection = new SecurityCollection();
		collection.addPattern("/secure/*");
		constraint.addCollection(collection);
		ctx.addConstraint(constraint);

		MemoryRealm realm = new MemoryRealm();
		realm.setPathname(new File("src/main/resources/tomcat-users.xml").getAbsolutePath());
		tomcat.getEngine().setRealm(realm);
		
		tomcat.start();
		tomcat.getServer().await();

		// go to: http://localhost:8080/sample/data/test

	}
}