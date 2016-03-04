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

	private static final String AUTH_ROLE = "admin";

	public static void main(String[] args) throws Exception {
		String basedir = "/Users/magex/workspace/java/web/embedded-tomcat";
		String roles = basedir + "/src/main/resources/tomcat-users.xml";
		
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("target/embedded-tomcat");
		tomcat.setHostname("localhost");
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/sample", new File(basedir + "/src/main/webapp").getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
		
        Tomcat.addServlet( ctx, "SampleServlet", new SampleServlet() );
        ctx.addServletMapping( "/data/*", "SampleServlet" );
        
		//Context ctx = tomcat.addWebapp("/sample", webapp);
		LoginConfig config = new LoginConfig();
		config.setAuthMethod("FORM");
		config.setLoginPage("/login.jsp");
		ctx.setLoginConfig(config);
		ctx.addSecurityRole(AUTH_ROLE);
		SecurityConstraint constraint = new SecurityConstraint();
		constraint.addAuthRole(AUTH_ROLE);
		SecurityCollection collection = new SecurityCollection();
		collection.addPattern("/*");
		constraint.addCollection(collection);
		ctx.addConstraint(constraint);

		MemoryRealm realm = new MemoryRealm();
		realm.setPathname(roles);
		tomcat.getEngine().setRealm(realm);

		tomcat.start();
		tomcat.getServer().await();
	}
}