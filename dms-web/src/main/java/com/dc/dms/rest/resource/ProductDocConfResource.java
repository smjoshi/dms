package com.dc.dms.rest.resource;

import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductDocConfService;
import com.dc.dms.rest.exception.ApplicationRestException;

@Component
@Path("/configs")
public class ProductDocConfResource {

	@Autowired
	private ProductDocConfService confService = null;

	private static final Logger logger = LoggerFactory.getLogger(ProductDocConfResource.class);

	/**
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/product/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDocConfiguration> getAllConfigurations(@QueryParam("productId") BigInteger productId) throws ApplicationRestException {

		logger.debug("{componentName:ProductDocConfResource, methodName:getAllConfigurations, parameters{productId.toString()}}");

		List<ProductDocConfiguration> confList = null;

		try {
			confList = confService.getProductDocConfigurations(productId);
			logger.debug("{componentName:ProductDocConfResource, methodName:getAllConfigurations, ProductConfigurations: " + confList.size());
		} catch (DMSException e) {
			e.printStackTrace();
			confList = null;
			throw new ApplicationRestException();
		}

		return confList;
	}

	/**
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/{docConfigId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDocConfiguration getConfiguration(@QueryParam("docConfigId") BigInteger docConfigId) throws ApplicationRestException {

		ProductDocConfiguration docConfig = null;
		throw new UnsupportedOperationException();
	}

	/**
	 * @param product
	 * @return
	 */
	@POST
	@Path("/list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDocConfiguration> upsertConfigurations(List<ProductDocConfiguration> confs)
			throws ApplicationRestException {

		List<ProductDocConfiguration> dbDocConfig = null;
		try {
			dbDocConfig = confService.upsertProductDocConfigurationsList(confs);
		} catch (DMSException e) {
			e.printStackTrace();
			dbDocConfig = null;
			throw new ApplicationRestException();
		}
		return dbDocConfig;
	}
	
	
	@POST
	@Path("/configuration")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDocConfiguration upsertConfiguration(ProductDocConfiguration docConfig)
			throws ApplicationRestException {

		ProductDocConfiguration dbDocConfig = null;
		try {
			dbDocConfig = confService.upsertProductDocConfiguration(docConfig);
		} catch (DMSException e) {
			e.printStackTrace();
			dbDocConfig = null;
			throw new ApplicationRestException();
		}
		return dbDocConfig;
	}
	
	

	/**
	 * @param product
	 * @return
	 */
	@DELETE	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteConfiguration(ProductDocConfiguration docConfig) throws ApplicationRestException {
		throw new UnsupportedOperationException("Operation not yet implemented");
	}

	public ProductDocConfService getConfService() {
		return confService;
	}

	public void setConfService(ProductDocConfService confService) {
		this.confService = confService;
	}

}
