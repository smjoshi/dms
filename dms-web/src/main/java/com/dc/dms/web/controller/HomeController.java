package com.dc.dms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/users/product" , method=RequestMethod.POST)
	public ModelAndView getUserProducts(@RequestParam(value = "userId", required = true) Long userId){
		
		String MANAGE_PRODUCT_VIEW = "manageProduct";
		ModelAndView mv = new ModelAndView(MANAGE_PRODUCT_VIEW);
		return mv;
	}
}
