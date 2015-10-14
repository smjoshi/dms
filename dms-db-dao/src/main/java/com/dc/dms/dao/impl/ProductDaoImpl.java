package com.dc.dms.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDao;
import com.dc.dms.entity.ProductEntity;

public class ProductDaoImpl extends AbstractDmsDao implements ProductDao {

	@Override
	public ProductEntity readByKey(ProductEntity product) throws DMSDaoException {
		return entityManager.find(ProductEntity.class, product.getProductId());
	}

	@Override
	public ProductEntity create(ProductEntity product) throws DMSDaoException {
		entityManager.persist(product);
		return product;
	}

	@Override
	public boolean update(ProductEntity product) throws DMSDaoException {
		entityManager.merge(product);
		return true;
	}

	@Override
	public List<ProductEntity> getOrgProducts(BigInteger orgId) {
		
		List<ProductEntity> productList= null;

		try {
			String queryStr = "SELECT u FROM ProductEntity p where p.orgId = ?1";
			TypedQuery<ProductEntity> query = entityManager.createQuery(queryStr,
					ProductEntity.class);
			query.setParameter(1, orgId);
			productList = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			productList = null;
		}

		return productList;
	}

}
