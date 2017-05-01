package com.cube.model;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cube.dao.Access;
import com.cube.dao.Database;
import com.cube.dto.User;

public class PushNotification implements Runnable{
	static final Logger logger = Logger.getLogger(PushNotification.class);
	public int userid;
	public PushNotification(int userid) {
		this.userid=userid;
	}
	@Override
	public void run() {
		System.out.println("user "+userid +" first bill payment");
		logger.info("user "+userid +" first bill payment");
	}

}
