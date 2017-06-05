package client.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import client.view.*;

public class TestCalClient {
	public static void main (String[] args) {
		String[] configLocations = new String[] { "applicationContext.xml", "commonConcern.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
		CalView cv = (CalView)context.getBean("calView");
		cv.doService();
	}
}
