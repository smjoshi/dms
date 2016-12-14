package com.dc.dms.dao.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.base.BaseDao;
import com.dc.dms.entity.ProductDocDetailEntity;
import com.dc.dms.entity.ProductEntity;

public interface ProductDocDetailDao extends BaseDao<ProductDocDetailEntity>{
	
	public List<ProductDocDetailEntity> getProductDocDetails(
			BigInteger productId);

	public ProductEntity getProductDocuments(Integer orgId , Integer productId);

}
