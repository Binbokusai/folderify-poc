package com.prithvi.main;

import org.apache.camel.spring.Main;

public class Start {
	public static void main(String[] args) throws Exception {
		Main main=new Main();
		main.setApplicationContextUri("META-INF\\spring\\camel-context.xml");
		main.start();
		while(true);
	}
}
