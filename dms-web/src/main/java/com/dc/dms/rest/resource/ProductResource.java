package com.dc.dms.rest.resource;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dms.domain.model.Product;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductService;
import com.dc.dms.rest.exception.AppRestException;

@Component
@Path("/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService = null;
	
	/**
	 * @param orgId
	 * @return
	 */
	@GET
	@Path("/joson/getProducts/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts(BigInteger orgId){
		
		List<Product> productList = null;
		
		try {
			productList = productService.getProductsByOrg(orgId);
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	
	
	/**
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/json/getProduct/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(BigInteger productId){
		
		Product product = null;
		throw new UnsupportedOperationException(" getProduct - Operation not yet implemented");
	}
	
	
	/**
	 * @param product
	 * @return
	 */
	@POST
	@Path("/json/addProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Product addOrUpdateProduct(Product product) throws AppRestException{
		
		Product dbReturnedProduct = null;
		try {
			dbReturnedProduct = productService.createOrUpdateProduct(product);
		} catch (DMSException e) {
			e.printStackTrace();
			dbReturnedProduct = null;
			throw new AppRestException();
		}
		return dbReturnedProduct;
	}
	
	/**
	 * @param product
	 * @return
	 */
	@POST
	@Path("/json/deleteProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteProduct(Product product){
		throw new UnsupportedOperationException("deleteProduct - Operation not yet implemented");
	}
	

}
