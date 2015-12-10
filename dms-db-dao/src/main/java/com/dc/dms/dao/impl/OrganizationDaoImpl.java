package com.dc.dms.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.dc.dms.base.AbstractDmsDao;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.OrganizationDao;
import com.dc.dms.entity.OrgEntity;
import com.dc.dms.entity.UserEntity;


@Repository
@Qualifier("OrganizationDao")
@Transactional
public class OrganizationDaoImpl extends AbstractDmsDao implements
		OrganizationDao {

	@Override
	public OrgEntity readByKey(OrgEntity org) throws DMSDaoException {
		return entityManager.find(OrgEntity.class, org.getOrgId());
	}

	@Override
	public OrgEntity create(OrgEntity org) throws DMSDaoException {
		entityManager.persist(org);
		return org;
	}

	@Override
	public boolean update(OrgEntity org) throws DMSDaoException {
		entityManager.merge(org);
		return true;
	}

	@Override
	public List<OrgEntity> getUserOrgnizations(BigInteger userId) {
		
		List<OrgEntity> userOrgs = null;
		String queryString  = "SELECT o FROM OrgEntity o where o.userId = ?1";
		TypedQuery<OrgEntity> query = entityManager.createQuery(queryString,
				OrgEntity.class);
		query.setParameter(1, userId);
		userOrgs = query.getResultList();
		
		return userOrgs;
	}

}