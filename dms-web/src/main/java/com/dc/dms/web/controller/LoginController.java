package com.dc.dms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dc.dms.domain.model.User;
import com.dc.dms.web.utils.RestUtils;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="/loginRequest", method = RequestMethod.GET)
	public ModelAndView getLoginView(){
		
		ModelAndView mv = new ModelAndView("login");
		return mv;
		
	}
	
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
	public ModelAndView getUserBuyLogin(@RequestParam(value="email", required=true) String email, @RequestParam(value="password", required=true) String password){
		
		ModelAndView mv = new ModelAndView();
		
//		Form loginForm = new Form();
//		
//		loginForm.param("loginId", email);
//		loginForm.param("password", password);
//		
//		//Get User Resource
//		String userResourceURL = "http://localhost:8080/dms-web/api/users/getUser";
//		Response response = RestUtils.callPostRestService(userResourceURL, loginForm);
		
		
		String resourcePath = "/users/json/getUser";
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		RestUtils.callPostJsonRestService(resourcePath, user, User.class);
		
		return mv;
		
		
	}
	
}
