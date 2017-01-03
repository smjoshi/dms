package com.dc.dms.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service("confService")
public class ProductDocConfServiceImpl implements ProductDocConfService {

	@Autowired
	private ProductDocConfDao productDocConfDao = null;

	private static final Logger logger = LoggerFactory.getLogger(ProductDocConfServiceImpl.class);
	
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
	public List<ProductDocConfiguration> getProductDocConfigurations(
			BigInteger productId) throws DMSException {

		logger.debug("{componentName:ProductDocConfServiceImpl, methodName:getProductDocConfigurations, parameters{productId.toString()}}");
		List<ProductDocConfEntity> docConfs = productDocConfDao.getProductDocConfigurations(productId);
		logger.debug("{componentName:ProductDocConfServiceImpl, methodName:getProductDocConfigurations, configurationCount:" + docConfs.size());
		return populateModelList(docConfs);
	}
	
	
	@Override
	public List<ProductDocConfiguration> upsertProductDocConfigurationsList(List<ProductDocConfiguration> confs)
			throws DMSException {
		
		List<ProductDocConfiguration> processedConfs = new ArrayList();
		
		ProductDocConfiguration processedConf = null;
		for(ProductDocConfiguration conf : confs){
			
			try {
				processedConf = upsertProductDocConfiguration(conf);
				processedConfs.add(processedConf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return processedConfs;
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
			docConfEntity.setDescription(docConf.getDescription());
		}

		return docConfEntity;
		
	}
	
	
	private ProductDocConfiguration populateDocConfModel(ProductDocConfEntity prodDocEntity){

		ProductDocConfiguration docConf = new ProductDocConfiguration();
		if (prodDocEntity != null){
			docConf.setDocConfId(prodDocEntity.getProductDocConfId());
			docConf.setProductId(prodDocEntity.getProductId());
			docConf.setDocTypeCode(prodDocEntity.getDocTypeCode());
			docConf.setMandatory(prodDocEntity.isMandatory());
			docConf.setMultipleItemAllowed(prodDocEntity.isMultipleItemAllowed());
			docConf.setGroupId(prodDocEntity.getGrouPId());
			docConf.setDescription(prodDocEntity.getDescription());
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
