package com.cube.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cube.dto.Properties;
import com.cube.dto.User;

public class Access {
	public static final boolean DEBUG = true;
	public ArrayList<User> getUser(Connection con) throws SQLException
	{
		ArrayList<User> userList = new ArrayList<User>();
		JSONParser parser = new JSONParser();
		ResultSet rs = con.prepareStatement("select * from userdb_cube.user").executeQuery();
		try{
			while(rs.next())
			{
				String str = rs.getString("str");
				printValue("json = "+str);
				
				JSONObject row = (JSONObject)parser.parse(str);
				String noun = (String) row.get("noun");
    			Long t = ((Long)row.get("userid"));
				int userid= t.intValue();
    			String ts= (String)row.get("ts");
    			String latlong= (String)row.get("latlong");
    			String verb = (String)row.get("verb");
    			Long l = (Long)row.get("timespent");
    			int timespent = 0;
    			if(l != null)
    			{
    				timespent = l.intValue();
        			printValue("timespent= "+timespent);

    			}
    			printValue("userid= "+userid);
    			printValue("noun= "+noun);
    			printValue("ts= "+ts);
    			printValue("latlong= "+latlong);
    			JSONObject prop = (JSONObject)row.get("properties");
    			String bank = (String)prop.get("bank");
    			Properties properties =null;
    			if(bank!=null)
    			{
    				int merchantid= ((Long)prop.get("merchantid")).intValue();
    				double value= ((Double)prop.get("value")).doubleValue();
    				String mode = (String)prop.get("mode");
    				printValue("merchantid = "+merchantid);
    				printValue("value= "+value);
    				printValue("mode= "+mode);
    				properties = new Properties(bank,merchantid,value,mode);
    			}
    			else
    			{
    				String text = (String)prop.get("text");
    				printValue("text= "+text);
    				properties = new Properties(text);
    			}
    			User user = new User(userid,noun,ts,latlong,verb,timespent,properties);
    			userList.add(user);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		return userList;
	}
	public int getUserCount(Connection con,int userid) throws SQLException, ParseException
	{
		JSONParser parser = new JSONParser();
		ResultSet rs = con.prepareStatement("select * from userdb_cube.user where userid = "+userid).executeQuery();
		int count=0;
		try{
			
			while(rs.next())
			{
				String str = rs.getString("str");
				printValue("json = "+str);
				
				JSONObject row = (JSONObject)parser.parse(str);
				String verb = (String)row.get("verb");
				if(count ==1)
					break;
				if(verb.equalsIgnoreCase("pay"))
				count++;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		con.close();
		return count;
	}
	public boolean totalBill(Connection con,int userid) throws SQLException, ParseException
	{
		JSONParser parser = new JSONParser();
		ResultSet rs = con.prepareStatement("select * from userdb_cube.user where userid = "+userid + "and ts is not null;").executeQuery();
		double sum=0;
		int st_ts=-1;
		try{
			
			while(rs.next())
			{
				String str = rs.getString("str");
				printValue("json = "+str);
				
				JSONObject row = (JSONObject)parser.parse(str);
				String verb = (String)row.get("verb");
				Long l = (Long)row.get("timespent");
				int timespent = 0;
    			
    				timespent = l.intValue();
        			printValue("timespent= "+timespent);

    			if(st_ts == -1)
    				st_ts= timespent;
				if(verb.equalsIgnoreCase("pay") && (timespent - st_ts)/60000000 <= 5)
				{
					JSONObject prop = (JSONObject)row.get("properties");
					double value= ((Double)prop.get("value")).doubleValue();
					sum+= value;
				}
				else
					break;
				printValue("sum= "+sum);
				if(sum >= 20000)
					break;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		con.close();
		if(sum >= 20000)
			return true;
		else
			return false;
	}
	private void printValue(String value)
	{
		if(DEBUG)
		{
			System.out.println(value);
		}
	}
}
