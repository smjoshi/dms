package com.dc.dms.dao.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.base.BaseDao;
import com.dc.dms.entity.ProductDocConfEntity;

public interface ProductDocConfDao extends BaseDao<ProductDocConfEntity> {

	public List<ProductDocConfEntity> getProductDocConfigurations(
			BigInteger productId);
}
