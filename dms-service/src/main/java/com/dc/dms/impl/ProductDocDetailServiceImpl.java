package com.dc.dms.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
	
	
	/**
	 * @param docConf
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
	
	

}
