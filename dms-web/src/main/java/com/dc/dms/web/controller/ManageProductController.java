package com.dc.dms.web.controller;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dc.dms.domain.model.Product;
import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.web.utils.RestUtils;

@Controller
public class ManageProductController {
	
	
	@RequestMapping(value = "/productRequest", method = RequestMethod.GET)
	public ModelAndView addOrUpdateProductView(){
		
		ModelAndView mv = new ModelAndView("addProductDetails");
		return mv;
		
	}
	
	
	public ModelAndView addorUpdateProduct(@ModelAttribute("productForm") Product product){
		
		ModelAndView mv = new ModelAndView();
		String homeView = "homePage";

		String createProduct = "/users/json/createProduct";

		Response productResponse = RestUtils.callPostJsonRestService(
				createProduct, product, Product.class);

		Product createdProduct = null;
		if (productResponse.getStatus() == 200) {
			createdProduct = productResponse.readEntity(Product.class);
			} else {
				mv.getModel().put("message", "Error while persisting Product Information");
			}
		
		mv.setViewName(homeView);
		return mv;
		
	}
	
	
	public ModelAndView addorUpdateProductConf(@ModelAttribute("productConfForm") ProductDocConfiguration productConf){
		
		ModelAndView mv = new ModelAndView();
		String homeView = "homePage";

		String createProductConf = "/users/json/createProductConf";

		Response productConfResponse = RestUtils.callPostJsonRestService(
				createProductConf, productConf, ProductDocConfiguration.class);

		ProductDocConfiguration createdProductConf = null;
		if (productConfResponse.getStatus() == 200) {
			createdProductConf = productConfResponse.readEntity(ProductDocConfiguration.class);
			} else {
				mv.getModel().put("message", "Error while persisting Product Conf Information");
			}
		
		mv.setViewName(homeView);
		return mv;
		
	}

	
	
}
