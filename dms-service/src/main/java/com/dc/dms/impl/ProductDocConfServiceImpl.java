package com.dc.dms.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocConfDao;
import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.entity.ProductDocConfEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductDocConfService;

/**
 * @author SJoshi
 *
 */
public class ProductDocConfServiceImpl implements ProductDocConfService {

	@Autowired
	private ProductDocConfDao productDocConfDao = null;
	
	@Override
	public ProductDocConfiguration upsertProductDocConfiguration(
			ProductDocConfiguration pdc) throws DMSException {

		ProductDocConfiguration processedConf = null;
		if (pdc != null){
			ProductDocConfEntity confEntity = prepareDocConfEntity(pdc);
			try {
				if (confEntity.getProductDocConfId() == null){
					confEntity = productDocConfDao.create(confEntity);
				}else
				{
					productDocConfDao.update(confEntity);
				}
				processedConf = populateDocConfModel(confEntity);
			} catch (DMSDaoException e) {
				e.printStackTrace();
				throw new DMSException();
			}
		}
		return processedConf;
	}

	/* (non-Javadoc)
	 * @see com.dc.dms.intf.ProductDocConfService#getProductDocConfiguration(java.math.BigInteger)
	 */
	@Override
	public List<ProductDocConfiguration> getProductDocConfiguration(
			BigInteger productId) throws DMSException {
		
		List<ProductDocConfEntity> docConfs = productDocConfDao.getProductDocConfigurations(productId);
		return populateModelList(docConfs);
	}
	
	
	
	
	/**
	 * @param docConf
	 * @return
	 */
	private ProductDocConfEntity prepareDocConfEntity(ProductDocConfiguration docConf){
		ProductDocConfEntity docConfEntity = new ProductDocConfEntity();
		
		if (docConf != null){
			docConfEntity.setProductDocConfId(docConf.getDocConfId());
			docConfEntity.setProductId(docConf.getProductId());
			docConfEntity.setDocTypeCode(docConf.getDocTypeCode());
			docConfEntity.setMandatory(docConf.isMandatory());
			docConfEntity.setMultipleItemAllowed(docConf.isMultipleItemAllowed());
			docConfEntity.setGrouPId(docConf.getGroupId());
		}

		return docConfEntity;
		
	}
	
	
	/**
	 * @param prodDocEntity
	 * @return
	 */
	private ProductDocConfiguration populateDocConfModel(ProductDocConfEntity prodDocEntity){

		ProductDocConfiguration docConf = new ProductDocConfiguration();
		if (prodDocEntity != null){
			docConf.setDocConfId(prodDocEntity.getProductDocConfId());
			docConf.setProductId(prodDocEntity.getProductId());
			docConf.setDocTypeCode(prodDocEntity.getDocTypeCode());
			docConf.setMandatory(prodDocEntity.isMandatory());
			docConf.setMultipleItemAllowed(prodDocEntity.isMultipleItemAllowed());
			docConf.setGroupId(prodDocEntity.getGrouPId());
		}
		return docConf;
	}
	
	
	
	private List<ProductDocConfiguration> populateModelList(List<ProductDocConfEntity> docConfs){
		
		List<ProductDocConfiguration> confList = new ArrayList<ProductDocConfiguration>();
		if (docConfs != null && !docConfs.isEmpty()){
			for (ProductDocConfEntity docConf : docConfs){
				confList.add(populateDocConfModel(docConf));
			}
		}
		return confList;
	}

}
