package com.dc.dms.web.controller;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dc.dms.domain.model.Organization;
import com.dc.dms.domain.model.User;
import com.dc.dms.web.utils.RestUtils;

@Controller
public class LoginController {

	@RequestMapping(value = "/loginRequest", method = RequestMethod.GET)
	public ModelAndView getLoginView() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ModelAndView getUserBuyLogin(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {

		ModelAndView mv = new ModelAndView();
		String resourcePath = "/users/json/getUser";

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		Response response = RestUtils.callPostJsonRestService(resourcePath,
				user, User.class);
		
		if (response.getStatus() == 200){
			mv.setViewName("homeView");
		}else if (response.getStatus() == 204) {
			mv.getModel().put("message",
					"Credentials are not correct, if not a member , SIGN UP!!");
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
		String homeView = "homePage";

		String createUserResource = "/users/json/createUser";
		String createOrgResource = "/org/json/createOrg";

		Response userResponse = RestUtils.callPostJsonRestService(
				createUserResource, user, User.class);

		User createdUser = null;
		if (userResponse.getStatus() == 200) {
			createdUser = userResponse.readEntity(User.class);

			// On successful user creation add orgnization for User
			Organization userOrg = new Organization();

			userOrg.setOrgName(user.getOrgName());
			userOrg.setOrgType(user.getOrgType());
			userOrg.setUserId(createdUser.getUserId());

			Response orgCreateRespose = RestUtils.callPostJsonRestService(
					createOrgResource, userOrg, Organization.class);

			if (orgCreateRespose.getStatus() == 200) {
				mv.setViewName(homeView);
			} else {
				mv.getModel().put("message", "Error while persisting Org");
			}

		} else {
			mv.getModel().put("message",
					"Credentials are not correct, if not a member , SIGN UP!!");
			mv.setViewName("login");
		}

		return mv;

	}

}
