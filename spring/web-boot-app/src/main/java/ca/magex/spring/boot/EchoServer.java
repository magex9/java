package ca.magex.spring.boot;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAutoConfiguration
@Controller
public class EchoServer {

	public static void main(String[] args) {
		SpringApplication.run(EchoServer.class, args);
	}

	@RequestMapping(value="/{type}/{id}", method=RequestMethod.GET)
	public void redirect(@PathVariable String type, @PathVariable String id, HttpServletResponse resp)
			throws Exception {
		String msg = "Getting: " + id + " of type " + type;
		System.out.println(msg);
		resp.getWriter().print(msg);
	}

}
