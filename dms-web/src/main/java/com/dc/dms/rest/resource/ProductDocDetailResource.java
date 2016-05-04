package com.dc.dms.rest.resource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.domain.model.ProductDocDetail;
import com.dc.dms.domain.model.ProductDocument;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductDocConfService;
import com.dc.dms.intf.ProductDocDetailService;
import com.dc.dms.rest.exception.ApplicationRestException;

@Component
@Path("/details")
public class ProductDocDetailResource {

	@Autowired
	private ProductDocDetailService docDetailService = null;

	@Autowired
	private ProductDocConfService confService = null;

	/**
	 * @param productId
	 * @return
	 */
	@GET
	@Path("/product/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDocDetail> getProductDocDetails(@PathParam("productId") BigInteger productId)
			throws ApplicationRestException {

		List<ProductDocument> docList = null;
		List<ProductDocConfiguration> docConfig = null;
		List<ProductDocDetail> docDetailList = new ArrayList<ProductDocDetail>();

		try {

			// get document configuration details
			docConfig = confService.getProductDocConfigurations(productId);

			// get document details
			docList = docDetailService.getProductDocDetails(productId);

			ProductDocDetail pd = null;
			for (ProductDocConfiguration config : docConfig) {

				pd = new ProductDocDetail();
				pd.setDocConf(config);
				for (ProductDocument prodDocDetail : docList) {

					if (config.getDocConfId() == prodDocDetail.getProductDocConfId()) {
						pd.setDocument(prodDocDetail);
					}
				}
				docDetailList.add(pd);
			}

		} catch (DMSException e) {
			e.printStackTrace();
			docDetailList = null;
			throw new ApplicationRestException();
		}
		return docDetailList;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDocument addOrUpdateProductDocDetail(ProductDocument pd) throws ApplicationRestException {

		ProductDocument dbProdDoc = null;
		try {
			 docDetailService.upsertProductDocDetails(pd);
		} catch (DMSException e) {
			e.printStackTrace();
			dbProdDoc = null;
			throw new ApplicationRestException();
		}
		return dbProdDoc;
	}

	public ProductDocConfService getConfService() {
		return confService;
	}

	public void setConfService(ProductDocConfService confService) {
		this.confService = confService;
	}

	public ProductDocDetailService getDocDetailService() {
		return docDetailService;
	}

	public void setDocDetailService(ProductDocDetailService docDetailService) {
		this.docDetailService = docDetailService;
	}

}
