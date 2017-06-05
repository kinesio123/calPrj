package server.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import server.controller.*;

public class TestCalServer {
	public static void main (String[] args) {
		String[] configLocations = new String[] { "applicationContext.xml", "commonConcern.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
		CalController cc = (CalController)context.getBean("calController");
		cc.doService();		  
	}
}
