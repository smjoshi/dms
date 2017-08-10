package com.dc.dms.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;


import com.dc.dms.entity.ProductDocConfEntity;
import com.dc.dms.entity.ProductDocumentEntity;
import com.dc.dms.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDocumentDao;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Qualifier("productDocumentDao")
@Transactional
public class ProductDocumentsDaoImpl extends AbstractDmsDao implements ProductDocumentDao {

	@Override
	public ProductDocumentEntity readByKey(ProductDocumentEntity pdd) throws DMSDaoException {
		return entityManager.find(ProductDocumentEntity.class, pdd.getProductDocumentId());
	}

	@Override
	public ProductDocumentEntity create(ProductDocumentEntity pdd) throws DMSDaoException {
		entityManager.persist(pdd);
		return pdd;
	}

	@Override
	public boolean update(ProductDocumentEntity pdd) throws DMSDaoException {
		entityManager.merge(pdd);
		return true;
	}

	@Override
	public List<ProductDocumentEntity> getProductDocuments(BigInteger productId) {

		List<ProductDocumentEntity> docDetails = null;
		String queryString = "SELECT pdd FROM ProductDocumentEntity pdd where pdd.productId = ?1";
		TypedQuery<ProductDocumentEntity> query = entityManager.createQuery(queryString, ProductDocumentEntity.class);
		query.setParameter(1, productId);
		docDetails = query.getResultList();

		return docDetails;
	}

	public boolean delete(ProductDocumentEntity productDocDetailEntity) throws DMSDaoException {
		boolean deleted = true;

		entityManager.remove(entityManager.contains(productDocDetailEntity) ? productDocDetailEntity : entityManager.merge(productDocDetailEntity));

		if (readByKey(productDocDetailEntity) != null) {
			deleted = false;
		}
		return deleted;
	}


	public ProductEntity getProductDocuments(Integer orgId , Integer productId) {

        ProductEntity resultProduct =  new ProductEntity();

        List<ProductEntity> fetchedProducts = getProductDocumentsdetails(orgId, productId);

        //consolidate product information
        List<ProductDocConfEntity> configurations = new ArrayList<ProductDocConfEntity>();
        int index = 0;
        for (ProductEntity p : fetchedProducts){
            if (index == 0){
                populateProduct(resultProduct, p);
            }
            //TODO: actually it is adding only single configuraton data (JPA query structure can be improved
			p.getDocConfigurations().size();
            configurations.addAll(p.getDocConfigurations());
        }

        //add all consolidated configurations to the result
        resultProduct.setDocConfigurations(configurations);

        return  resultProduct;

	}


	private List<ProductEntity>getProductDocumentsdetails(Integer orgId , Integer productId){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query =  cb.createQuery(ProductEntity.class);

        Root<ProductEntity> product = query.from(ProductEntity.class);
		product.fetch("docConfigurations");
        Join<ProductEntity, ProductDocConfEntity>  prodConf = product.join("docConfigurations");
        Join<ProductDocConfEntity, ProductDocumentEntity> docDetails = prodConf.join("docDetail");

        //ParameterExpression<Integer> org = cb.parameter(Integer.class);
        //ParameterExpression<Integer> pr = cb.parameter(Integer.class);

        return entityManager.createQuery(query.select(product).where(cb.and(cb.equal(product.get("orgId"), orgId), cb.equal(product.get("productId"), productId)))).getResultList();
    }

    private void populateProduct(ProductEntity resultProduct, ProductEntity fetchedProduct){

        resultProduct.setProductId(fetchedProduct.getProductId());
        resultProduct.setProductName(fetchedProduct.getProductName());
        resultProduct.setProductDesc(fetchedProduct.getProductDesc());
        resultProduct.setProductCode(fetchedProduct.getProductCode());
        resultProduct.setOrgId(fetchedProduct.getOrgId());
    }
}
