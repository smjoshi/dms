package com.dc.dms.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dc.dms.domain.model.Product;
import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.domain.model.User;
import com.dc.dms.web.utils.RestUtils;

@Controller
@SessionAttributes("user")
public class ManageProductController {

	String MANAGE_PRODUCT_VIEW = "manageProduct";
	String createProductConf = "/configs/list";
	String createProduct = "/products/product";

	@RequestMapping(value = "/users/productRequest", method = RequestMethod.GET)
	public ModelAndView addOrUpdateProductView() {

		ModelAndView mv = new ModelAndView("addProductDetails");
		return mv;

	}

	@RequestMapping(value = "/users/product", method = RequestMethod.POST)
	public ModelAndView addorUpdateProduct(@ModelAttribute("productForm") Product product, BindingResult result,
			@ModelAttribute("user") User user, BindingResult userBind) {

		ModelAndView mv = new ModelAndView();
		Response productResponse = RestUtils.callPostJsonRestService(createProduct, product, Product.class);

		Product createdProduct = null;
		if (productResponse.getStatus() == 200) {
			createdProduct = productResponse.readEntity(Product.class);

			/*
			 * *****************************************************************
			 *  Add Default product configuration,
			 * adding default product configuration is just for testing purpose
			 * later, it can be replaced with actual functionality
			 * 
			 * *****************************************************************/
			   addDefaultProductConfiguration(createdProduct);
			//********************** End of testing code ***************************   
			   
			mv.getModel().put("message", createdProduct.getProductId() + " Product added successfully");
		} else {
			mv.getModel().put("message", "Error while persisting Product Information");
		}

		mv.setViewName(MANAGE_PRODUCT_VIEW);
		return mv;

	}
	
	
	
	

	public ModelAndView addorUpdateProductConf(@ModelAttribute("productConfForm") ProductDocConfiguration productConf) {

		ModelAndView mv = new ModelAndView();
		String homeView = "homePage";

		Response productConfResponse = RestUtils.callPostJsonRestService(createProductConf, productConf,
				ProductDocConfiguration.class);

		ProductDocConfiguration createdProductConf = null;
		if (productConfResponse.getStatus() == 200) {
			createdProductConf = productConfResponse.readEntity(ProductDocConfiguration.class);
		} else {
			mv.getModel().put("message", "Error while persisting Product Conf Information");
		}

		mv.setViewName(homeView);
		return mv;

	}
	
	@RequestMapping(value = "/users/product/detail", method = RequestMethod.GET)
	public ModelAndView getProductDocs(@RequestParam("productId") Long productId){
		ModelAndView mv = new ModelAndView();
		String DOC_DETAILS = "dms";
		
		mv.getModel().put("productId", productId);
		mv.setViewName(DOC_DETAILS);
		
		return mv;
	}
	
	
	private void addDefaultProductConfiguration(Product product){
		
		List<ProductDocConfiguration> confs = getDefaultProductConfigurations(product);
		
		//add this configuration 
		Response productConfResponse = RestUtils.callPostJsonRestService(createProductConf, confs,
				ArrayList.class);

		List<ProductDocConfiguration> createdProductConf = null;
		if (productConfResponse.getStatus() == 200) {
			createdProductConf = Arrays.asList(productConfResponse.readEntity(ProductDocConfiguration[].class));
		} 
		
		
	}
	
	List<ProductDocConfiguration> getDefaultProductConfigurations(Product pr){
		
		List<ProductDocConfiguration> confs = new ArrayList<>();
		
		ProductDocConfiguration frontImageConf = new ProductDocConfiguration();
		ProductDocConfiguration leftImageConf = new ProductDocConfiguration();
		ProductDocConfiguration rightImageConf = new ProductDocConfiguration();
		ProductDocConfiguration backImageConf = new ProductDocConfiguration();
		
		frontImageConf.setDescription("Front Image");
		frontImageConf.setDocTypeCode("FRONTIMAGE");
		frontImageConf.setGroupId(null);
		frontImageConf.setMandatory(true);
		frontImageConf.setMultipleItemAllowed(false);
		frontImageConf.setProductId(pr.getProductId());
		
		leftImageConf.setDescription("Left side Image");
		leftImageConf.setDocTypeCode("RIGHTIMAGE");
		leftImageConf.setGroupId(null);
		leftImageConf.setMandatory(false);
		leftImageConf.setMultipleItemAllowed(false);
		leftImageConf.setProductId(pr.getProductId());
		
		rightImageConf.setDescription("Right side Image");
		rightImageConf.setDocTypeCode("BACKIMAGE");
		rightImageConf.setGroupId(null);
		rightImageConf.setMandatory(false);
		rightImageConf.setMultipleItemAllowed(false);
		rightImageConf.setProductId(pr.getProductId());
		
		backImageConf.setDescription("Back side Image");
		backImageConf.setDocTypeCode("LEFTIMAGE");
		backImageConf.setGroupId(null);
		backImageConf.setMandatory(false);
		backImageConf.setMultipleItemAllowed(false);
		backImageConf.setProductId(pr.getProductId());
		
		confs.add(leftImageConf);
		confs.add(rightImageConf);
		confs.add(backImageConf);
		confs.add(backImageConf);
		
		return confs;
		
	}
	

}
