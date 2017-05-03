package com.cube.model;

import org.apache.log4j.Logger;

public class AlertUser  implements Runnable{
	static final Logger logger = Logger.getLogger(AlertUser.class);
	long totalbill;
	public AlertUser(long totalbill) {
		this.totalbill = totalbill;
	}
	@Override
	public void run() {
		logger.info("[Alerting user] : you have done transcation worth "+totalbill);
	}

}
