package com.dc.dms.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import com.dc.dms.entity.ProductDocConfEntity;
import com.dc.dms.entity.ProductEntity;
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


	private List<ProductEntity> getProductDocumentsdetails(Integer orgId , Integer productId){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query =  cb.createQuery(ProductEntity.class);

        Root<ProductEntity> product = query.from(ProductEntity.class);
		product.fetch("docConfigurations");
        Join<ProductEntity, ProductDocConfEntity>  prodConf = product.join("docConfigurations");
        Join<ProductDocConfEntity, ProductDocDetailEntity> docDetails = prodConf.join("docDetail");

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
