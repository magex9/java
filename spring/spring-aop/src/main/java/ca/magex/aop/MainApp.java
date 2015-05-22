package ca.magex.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		ItemService item = context.getBean(ItemService.class);
		item.getName();
		item.query("TEST", 3);
	}

}
