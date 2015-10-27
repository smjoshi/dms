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
import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductDocConfService;
import com.dc.dms.rest.exception.AppRestException;

@Component
@Path("/conf")
public class ProductDocConfResource {
	
	@Autowired
	private ProductDocConfService confService = null;
	
	
	/**
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/joson/getProductConfs/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDocConfiguration> getAllProductDocConfig(BigInteger productId) throws AppRestException {
		
		List<ProductDocConfiguration> confList = null;
		
		try {
			confList = confService.getProductDocConfiguration(productId);
		} catch (DMSException e) {
			e.printStackTrace();
			confList = null;
			throw new AppRestException();
		}
		return confList;
	}
	
	
	/**
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/joson/getProductConf/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDocConfiguration getProductConfig(BigInteger docConfigId) throws AppRestException{
		
		ProductDocConfiguration docConfig = null;
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * @param product
	 * @return
	 */
	@POST
	@Path("/json/addProductConf")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDocConfiguration addOrUpdateProduct(ProductDocConfiguration docConfig) throws AppRestException {
		
		ProductDocConfiguration dbDocConfig = null;
		try {
			dbDocConfig = confService.upsertProductDocConfiguration(docConfig);
		} catch (DMSException e) {
			e.printStackTrace();
			dbDocConfig = null;
			throw new AppRestException();
		}
		return dbDocConfig;
	}
	
	/**
	 * @param product
	 * @return
	 */
	@POST
	@Path("/json/deleteProductConf")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteProductDocConfig(ProductDocConfiguration docConfig) throws AppRestException{
		throw new UnsupportedOperationException("Operation not yet implemented");
	}


	public ProductDocConfService getConfService() {
		return confService;
	}


	public void setConfService(ProductDocConfService confService) {
		this.confService = confService;
	}
	

}
