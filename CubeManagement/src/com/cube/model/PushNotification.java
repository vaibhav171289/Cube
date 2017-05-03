package com.cube.model;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class PushNotification implements Runnable{
	static final Logger logger = Logger.getLogger(PushNotification.class);
	public int userid;
	public PushNotification(int userid) {
		this.userid=userid;
	}
	@Override
	public void run() {
		 SimpleLayout layout = new SimpleLayout();    
	      FileAppender appender = null;
		try {
			appender = new FileAppender(layout,"user.txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		logger.addAppender(appender);
		logger.setLevel((Level)Level.INFO);
		
		System.out.println("user "+userid +" first bill payment");
		logger.info("user "+userid +" first bill payment");
		
	}

}
