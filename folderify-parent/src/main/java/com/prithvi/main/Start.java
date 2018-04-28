package com.prithvi.main;

import org.apache.camel.spring.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Start {
	public static void main(String[] args) throws Exception {
		Main main=new Main();
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:**/META-INF/spring/*-context.xml");
		main.setApplicationContext((AbstractApplicationContext) context);
		main.start();
		while(true);
	}
}
