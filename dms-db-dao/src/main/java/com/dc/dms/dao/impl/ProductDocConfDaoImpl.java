package com.dc.dms.dao.impl;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocConfDao;
import com.dc.dms.entity.ProductDocConfEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;

import java.math.BigInteger;
import java.util.List;


@Repository
@Qualifier("productDocConfDao")
@Transactional
public class ProductDocConfDaoImpl extends AbstractDmsDao implements
        ProductDocConfDao {

    @Override
    public ProductDocConfEntity readByKey(ProductDocConfEntity docConf)
            throws DMSDaoException {
        return entityManager.find(ProductDocConfEntity.class,
                docConf.getProductDocConfId());
    }

    @Override
    public ProductDocConfEntity create(ProductDocConfEntity docConf)
            throws DMSDaoException {
        entityManager.persist(docConf);
        return docConf;
    }

    @Override
    public boolean update(ProductDocConfEntity docConf) throws DMSDaoException {
        entityManager.merge(docConf);
        return true;
    }

    @Override
    public List<ProductDocConfEntity> getProductDocConfigurations(
            BigInteger productId) {

        List<ProductDocConfEntity> docConfs = null;
        String queryString = "SELECT pdc FROM ProductDocConfEntity pdc where pdc.productId = ?1";
        TypedQuery<ProductDocConfEntity> query = entityManager.createQuery(
                queryString, ProductDocConfEntity.class);
        query.setParameter(1, productId);
        docConfs = query.getResultList();

        return docConfs;
    }

    public boolean delete(ProductDocConfEntity productDocConfEntity) throws DMSDaoException {

        boolean deleted = true;

        entityManager.remove(entityManager.contains(productDocConfEntity) ? productDocConfEntity : entityManager.merge(productDocConfEntity));

        if (readByKey(productDocConfEntity) != null) {
            deleted = false;
        }
        return deleted;
    }
}
