package com.dc.dms.rest.resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dms.entity.UserEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.UserService;



@Component
@Path("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	
	@GET
	@Path("/{loginId}")
	@Produces({MediaType.TEXT_PLAIN})
	public String getUserByLogin(@PathParam("loginId") String loginId){

		try {
			UserEntity ue = new UserEntity();
			ue.setLoginId(loginId);
			userService.getUserByLogin(ue);
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Test Success";
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers(){
		return  "Test Success";
	}
	

}
