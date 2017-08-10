package com.dc.dms.web.controller;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dc.dms.domain.model.Organization;
import com.dc.dms.domain.model.User;
import com.dc.dms.web.utils.RestUtils;

@Controller
@SessionAttributes("user")
public class LoginController {

	private static String USER_RESOURCE = "/users/login";
	private static String USER_ORG_RESOURCE = "/orgs/user/{userId}";
	private String HOME_VIEW = "home";

	@RequestMapping(value = "/loginRequest", method = RequestMethod.GET)
	public ModelAndView getLoginView() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ModelAndView getUserBuyLogin(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {

		ModelAndView mv = new ModelAndView();

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		User returnedUser = getUserDetails(user);

		if (returnedUser != null) {

			// Get user Organizations
			List<Organization> userOrgs = getUserOrganizations(returnedUser);
			returnedUser.setOrganizations(userOrgs);

			mv.setViewName(HOME_VIEW);

			// This will add user in session as well
			mv.getModelMap().addAttribute("user", returnedUser);
			mv.getModel().put("user", returnedUser);

		} else {
			// User not found with given credentials

			mv.getModel().put("message", "User does not exists, if not a member , SIGN UP!!");
			mv.setViewName("login");
		}

		return mv;
	}

	@RequestMapping(value = "/signUpRequest", method = RequestMethod.GET)
	public ModelAndView getSignUpView() {

		ModelAndView mv = new ModelAndView("signUp");
		return mv;

	}

	@RequestMapping(value = "/users/signUp", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("form-signup") User user) {

		ModelAndView mv = new ModelAndView();
		String homeView = "home";

		String createUserResource = "/users/register";
		String createOrgResource = "/orgs/org";

		Response userResponse = RestUtils.callPostJsonRestService(createUserResource, user, User.class);

		User createdUser = null;
		if (userResponse.getStatus() == 200) {
			createdUser = userResponse.readEntity(User.class);

			// On successful user creation add orgnization for User
			Organization userOrg = new Organization();

			userOrg.setOrgName(user.getOrgName());
			userOrg.setOrgType(user.getOrgType());
			userOrg.setUserId(createdUser.getUserId());

			Response orgCreateRespose = RestUtils.callPostJsonRestService(createOrgResource, userOrg,
					Organization.class);

			if (orgCreateRespose.getStatus() == 200) {
				mv.setViewName(homeView);
				createdUser.getOrganizations().add(userOrg);
			} else {
				mv.getModel().put("message", "Error while persisting Org");
			}

		} else {
			mv.getModel().put("message", "Credentials are not correct, if not a member , SIGN UP!!");
			mv.setViewName("login");
		}
		if (createdUser != null){
			mv.getModelMap().addAttribute("user", createdUser);
		}

		return mv;

	}

	private User getUserDetails(User user) {

		User returnedUser = null;

		Response response = RestUtils.callPostJsonRestService(USER_RESOURCE, user, User.class);

		if (response.getStatus() == 200) {
			returnedUser = response.readEntity(User.class);
		}

		return returnedUser;
	}

	private List<Organization> getUserOrganizations(User user) {
		List<Organization> userOrgs = null;

		UriComponents uriComponents = UriComponentsBuilder.newInstance().path(USER_ORG_RESOURCE).build()
				.expand(user.getUserId()).encode();

		String uri = uriComponents.getPath();

		Response response = RestUtils.callGetRestService(uri);

		if (response.getStatus() == 200) {
			userOrgs = response.readEntity(new GenericType<List<Organization>>() {
			});
		}

		return userOrgs;
	}

}
