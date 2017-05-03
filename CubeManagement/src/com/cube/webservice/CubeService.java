package com.cube.webservice;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.cube.dto.User;
import com.cube.model.AccessManager;
import com.google.gson.Gson;
@Path("/cubeservice")
public class CubeService {
	@GET
	@Path("/users/{userid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String users(@PathParam("userid") int userid)
	{
		String user = null;
		ArrayList<User> userList = new ArrayList<User>();
		AccessManager manager=null;
		try{
			 manager = new AccessManager(userid);
			userList = manager.getUser();
			Gson gson = new Gson();
			user = gson.toJson(userList);
			manager.pushNotification();
			manager.alertUser();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	 @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }

}
