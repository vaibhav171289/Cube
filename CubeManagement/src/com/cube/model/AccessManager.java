package com.cube.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import com.cube.dao.Access;
import com.cube.dto.User;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class AccessManager {
	int userid;
	public AccessManager(){}
	public AccessManager (int userid)
	{
		this.userid= userid;
	}
	public ArrayList<User> getUser() throws Exception {
		ArrayList<User> userList = new ArrayList<User>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");

		Access access = new Access();
		userList = access.getUser(con);
		
		return userList;
	}
	public void  pushNotification() throws ClassNotFoundException, SQLException, InterruptedException, ParseException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
		
		Access access = new Access();
		int count = access.getUserCount(con, userid);
		if(count ==2)
		{
			PushNotification push = new PushNotification(userid);
			Thread th = new Thread(push);
			th.start();
			AbandonedConnectionCleanupThread.shutdown();
		}
	}
	public void alertUser() throws ClassNotFoundException, SQLException
	{

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
		
		Access access = new Access();
		int count = access.getUserCount(con, userid);
		if(count ==2)
		{
			PushNotification push = new PushNotification(userid);
			Thread th = new Thread(push);
			th.start();
			AbandonedConnectionCleanupThread.shutdown();
		}
	}
}
