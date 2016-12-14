package com.dc.dms.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.domain.model.ProductDocDetail;
import com.dc.dms.entity.ProductDocConfEntity;
import com.dc.dms.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocDetailDao;
import com.dc.dms.domain.model.ProductDocument;
import com.dc.dms.entity.ProductDocDetailEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductDocDetailService;


@Service("docDetailsService")
public class ProductDocDetailServiceImpl implements ProductDocDetailService {
	
	@Autowired
	private ProductDocDetailDao productDocDetailDao;



	@Override
	public ProductDocument upsertProductDocDetails(ProductDocument pdd) throws DMSException {

		ProductDocument processedDocDetail = null;
		if (pdd != null){
			ProductDocDetailEntity docDetailEntity = prepareDocDetailEntity(pdd);
			try {
				if (docDetailEntity.getProductDocDetailId() == null){
					docDetailEntity = productDocDetailDao.create(docDetailEntity);
				}else
				{
					productDocDetailDao.update(docDetailEntity);
				}
				processedDocDetail = populateDocDetailModel(docDetailEntity);
			} catch (DMSDaoException e) {
				e.printStackTrace();
				throw new DMSException();
			}
		}
		return processedDocDetail;
	}

	@Override
	public List<ProductDocument> getProductDocDetails(BigInteger productId) throws DMSException {
		
		List<ProductDocDetailEntity> docDetails = productDocDetailDao.getProductDocDetails(productId);
		return populateDocDetailModelList(docDetails);
	}


	@Override
	public List<ProductDocDetail> getProductDocuments(Integer customerId, Integer productId) throws DMSException {

		ProductEntity pe = productDocDetailDao.getProductDocuments(customerId, productId);
		return retrieveDocumentDetails(pe);
	}
	
	
	/**
	 * @param docDetail
	 * @return
	 */
	private ProductDocDetailEntity prepareDocDetailEntity(ProductDocument docDetail){
		ProductDocDetailEntity docDetailEntity = new ProductDocDetailEntity();
		
		if (docDetail != null){
			//docDetailEntity.setProductDocDetailId(docDetail.getProductDocDetailId());
			docDetailEntity.setProductId(docDetail.getProductId());
			docDetailEntity.setProductDocConfId(docDetail.getProductDocConfId());
			docDetailEntity.setDocUrl(docDetail.getDocUrl());
		}

		return docDetailEntity;
		
	}
	
	
	private ProductDocument populateDocDetailModel(ProductDocDetailEntity docDetailEntity){

		ProductDocument docDetail = new ProductDocument();
		if (docDetailEntity != null){
			//docDetail.setProductDocDetailId(docDetailEntity.getProductDocDetailId());
			docDetail.setDocUrl(docDetailEntity.getDocUrl());
			docDetail.setProductDocConfId(docDetailEntity.getProductDocConfId());
			docDetail.setProductId(docDetailEntity.getProductId());
			
			if (docDetailEntity.getDocConfiguration() != null){
				//TODO: populate document configuration as well.

			}
			
			
		}
		return docDetail;
	}
	
	
	private List<ProductDocument> populateDocDetailModelList(List<ProductDocDetailEntity> detailEntityList){
		
		List<ProductDocument> productDocuments  = new ArrayList<ProductDocument>();
		if (detailEntityList != null){
			for (ProductDocDetailEntity detailEntiry : detailEntityList){
				productDocuments.add(populateDocDetailModel(detailEntiry));
			}
		}
		
		return productDocuments;
	}


	private List<ProductDocDetail> retrieveDocumentDetails(ProductEntity pe){

		//retrive document detail and configuration
		List<ProductDocConfEntity> configurations = pe.getDocConfigurations();

		List<ProductDocDetail> docDetails = new ArrayList<ProductDocDetail>();
		List <ProductDocConfiguration> productDocConfigurations = new ArrayList<ProductDocConfiguration>();
		ProductDocConfiguration docConfiguration = null;
		ProductDocDetail docDetail = null;
		for (ProductDocConfEntity confEntity : configurations){
			//populate
			docConfiguration = new ProductDocConfiguration();
			docDetail = new ProductDocDetail();
			docConfiguration.copyFromEntiry(confEntity);

			//get Document detail Entity
			ProductDocDetailEntity pde  = confEntity.getDocDetail();
			ProductDocument pd = new ProductDocument();
			pd.copyFromEntity(pde);

			docConfiguration.setProductDocument(pd);

			docDetail.setDocConf(docConfiguration);
			docDetails.add(docDetail);
		}

		return docDetails;



	}

	

}
