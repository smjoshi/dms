package com.dc.dms.rest.resource;

import javax.ws.rs.Consumes;
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
import com.dc.dms.rest.exception.AppExceptionMapper;
import com.dc.dms.rest.exception.AppRestException;

@Component
@Path("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@POST
	@Path("/getUser")
	@Produces({ MediaType.TEXT_PLAIN })
	public User getUserByLogin(@FormParam("loginId") String loginId,
			@FormParam("password") String pwd) throws AppRestException {

		User u = null;

		try {
			u = new User();
			u.setLoginId(loginId);
			u = userService.getUserByLogin(u);
		} catch (DMSException e) {
			u = null;
			throw new AppRestException();

		}
		return u;
	}

	@POST
	@Path("/json/getUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByLoginJson(User user) throws AppRestException {
		User fetchedUser = null;

		try {
			fetchedUser = userService.getUserByCredentials(user);
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fetchedUser = null;
			throw new AppRestException();
		}
		return fetchedUser;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers() {
		return "Test Success";
	}

	/**
	 * 
	 * This method adds new user to system
	 * @param user
	 * @return
	 * @throws AppRestException
	 */
	@POST
	@Path("/json/createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(User user) throws AppRestException {

		User createdUser = null;
		
		try {
			createdUser = userService.addUser(user);
		} catch (DMSException e) {
			e.printStackTrace();
			createdUser = null;
			throw new AppRestException();
		}
		
		return createdUser;
	}

	/**
	 * This method deletes the existing user
	 * 
	 * @param user
	 * @return
	 * @throws AppRestException
	 */
	public boolean deleteUser(User user) throws AppRestException {

		throw new UnsupportedOperationException();
	}

}
