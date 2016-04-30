package com.dc.dms.dao.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.base.BaseDao;
import com.dc.dms.entity.ProductDocDetailEntity;

public interface ProductDocDetailDao extends BaseDao<ProductDocDetailEntity>{
	
	public List<ProductDocDetailEntity> getProductDocDetails(
			BigInteger productId);

}
