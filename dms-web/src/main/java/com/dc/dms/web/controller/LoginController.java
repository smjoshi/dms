package com.dc.dms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	
	@RequestMapping(value="/loginRequest", method = RequestMethod.GET)
	public ModelAndView getLoginView(){
		
		ModelAndView mv = new ModelAndView("login");
		return mv;
		
	}
	
}
