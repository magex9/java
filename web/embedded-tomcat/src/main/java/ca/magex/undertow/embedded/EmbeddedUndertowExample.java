package ca.magex.undertow.embedded;

import javax.servlet.ServletException;

import ca.magex.web.servlet.SampleAuthenticationServlet;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;

public class EmbeddedUndertowExample {

	public static void main(final String[] args) {
		try {

			DeploymentInfo servletBuilder = deployment()
				.setClassLoader(EmbeddedUndertowExample.class.getClassLoader())
				.setContextPath("/app")
				.setDeploymentName("test.war")
				.addServlets(
					servlet("SampleAuthenticationServlet", SampleAuthenticationServlet.class)
					.addMapping("/*"));

			DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			HttpHandler servletHandler = manager.start();
			PathHandler path = Handlers.path(Handlers
				.redirect("/app"))
				.addPrefixPath("/app", servletHandler);
			Undertow server = Undertow.builder()
				.addHttpListener(8080, "localhost")
				.setHandler(path)
				.build();
			server.start();
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}

}
