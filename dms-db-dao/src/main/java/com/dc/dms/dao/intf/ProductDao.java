package com.dc.dms.dao.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.base.BaseDao;
import com.dc.dms.entity.ProductEntity;

public interface ProductDao extends BaseDao<ProductEntity> {

	public List<ProductEntity> getOrgProducts(BigInteger userId);

}
