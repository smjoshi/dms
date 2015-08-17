package com.dc.dms.rest.resource;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dms.domain.model.User;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.UserService;



@Component
@Path("/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	
	@POST
	@Path("/getUser")
	@Produces({MediaType.TEXT_PLAIN})
	public User getUserByLogin(@FormParam("loginId") String loginId, @FormParam("password") String pwd){
		
		User u = null;
		
		try {
			u = new User();
			u.setLoginId(loginId);
			u = userService.getUserByLogin(u);
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			u = null;
		}
		return u;
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers(){
		return  "Test Success";
	}
	

}
