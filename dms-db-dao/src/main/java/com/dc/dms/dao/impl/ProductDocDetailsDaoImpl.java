package com.dc.dms.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocDetailDao;
import com.dc.dms.entity.ProductDocDetailEntity;



@Repository
@Qualifier("productDocDetailDao")
@Transactional
public class ProductDocDetailsDaoImpl extends AbstractDmsDao implements ProductDocDetailDao {

	@Override
	public ProductDocDetailEntity readByKey(ProductDocDetailEntity pdd) throws DMSDaoException {
		return entityManager.find(ProductDocDetailEntity.class, pdd.getProductDocDetailId());
	}

	@Override
	public ProductDocDetailEntity create(ProductDocDetailEntity pdd) throws DMSDaoException {
		entityManager.persist(pdd);
		return pdd;
	}

	@Override
	public boolean update(ProductDocDetailEntity pdd) throws DMSDaoException {
		entityManager.merge(pdd);
		return true;
	}

	@Override
	public List<ProductDocDetailEntity> getProductDocDetails(BigInteger productId) {

		List<ProductDocDetailEntity> docDetails = null;
		String queryString = "SELECT pdd FROM ProductDocDetailEntity pdd where pdd.productId = ?1";
		TypedQuery<ProductDocDetailEntity> query = entityManager.createQuery(queryString, ProductDocDetailEntity.class);
		query.setParameter(1, productId);
		docDetails = query.getResultList();

		return docDetails;
	}

	public boolean delete(ProductDocDetailEntity productDocDetailEntity) throws DMSDaoException {
		boolean deleted = true;

		entityManager.remove(entityManager.contains(productDocDetailEntity) ? productDocDetailEntity : entityManager.merge(productDocDetailEntity));

		if (readByKey(productDocDetailEntity) != null) {
			deleted = false;
		}
		return deleted;
	}
}
