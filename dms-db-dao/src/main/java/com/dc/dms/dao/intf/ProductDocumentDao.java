package com.dc.dms.dao.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.base.BaseDao;
import com.dc.dms.entity.ProductDocumentEntity;
import com.dc.dms.entity.ProductEntity;

public interface ProductDocumentDao extends BaseDao<ProductDocumentEntity>{
	
	public List<ProductDocumentEntity> getProductDocuments(
			BigInteger productId);

	public ProductEntity getProductDocuments(Integer orgId , Integer productId);

}
