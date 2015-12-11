package com.dc.dms.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dms.domain.model.User;
import com.dc.dms.exception.DMSException;
import com.dc.dms.exception.DuplicateUserException;
import com.dc.dms.intf.UserService;
import com.dc.dms.rest.exception.ApplicationRestException;

@Component
@Path("/users")
public class UserResource {

	@Autowired
	private UserService userService;


	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserDetails(User user) throws ApplicationRestException {
		User fetchedUser = null;

		try {
			fetchedUser = userService.getUserByCredentials(user);
		} catch (DMSException dmsEx) {
			dmsEx.printStackTrace();
			fetchedUser = null;
			throw new NotFoundException();
		}
		return fetchedUser;
	}



	/**
	 * 
	 * This method adds new user to system
	 * 
	 * @param user
	 * @return
	 * @throws ApplicationRestException
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User registerUser(User user) throws ApplicationRestException {

		User createdUser = null;

		try {
				createdUser = userService.registerUser(user);
		} catch (DuplicateUserException | DMSException e ) {
			e.printStackTrace();
			createdUser = null;
			throw new ApplicationRestException();
		} 

		return createdUser;
	}

	/**
	 * This method deletes the existing user
	 * 
	 * @param user
	 * @return
	 * @throws ApplicationRestException
	 */
	@DELETE
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteUser(User user) throws ApplicationRestException {

		throw new UnsupportedOperationException();
	}

}
