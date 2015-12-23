package com.dc.dms.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.OrganizationDao;
import com.dc.dms.domain.model.Organization;
import com.dc.dms.entity.OrgEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.OrganizationService;


@Service("orgService")
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	OrganizationDao orgDao = null;
	

	@Override
	public Organization getOrganizationDetail(BigInteger orgId) throws DMSException {
		
		Organization org = null;
		
		try {
			OrgEntity orgEntity = new OrgEntity();
			orgEntity.setOrgId(orgId);
			orgEntity =  orgDao.readByKey(orgEntity);
			if (orgEntity != null){
				org = populateOrgModel(orgEntity);
			}
		} catch (DMSDaoException e) {
			throw new DMSException(303, e.getMessage());
		}
		
		return org;
	}

	@Override
	public Organization upsertOrginzation(Organization org)
			throws DMSException {
		
		Organization processedOrg = null;
		if (org != null){
			OrgEntity orgEntity = prepareOrgEntity(org);
			try {
				if (orgEntity.getOrgId() == null){
					orgEntity = orgDao.create(orgEntity);
				}else
				{
				   orgDao.update(orgEntity);
				}
				processedOrg = populateOrgModel(orgEntity);
			} catch (DMSDaoException e) {
				e.printStackTrace();
			}
		}
		return processedOrg;
	}

	@Override
	public List<Organization> getUserOrganizations(BigInteger userId)
			throws DMSException {
		List<OrgEntity> orgs = orgDao.getUserOrgnizations(userId);
		return pupulateModelList(orgs);
		
	}
	
	
	
	/**
	 * @param org
	 * @return
	 */
	private OrgEntity prepareOrgEntity(Organization org){
		OrgEntity orgEntity = new OrgEntity();
		
		if (org != null){
			orgEntity.setOrgId(org.getOrgId());
			orgEntity.setOrgName(org.getOrgName());
			orgEntity.setOrgType(org.getOrgType());
			orgEntity.setUserId(org.getUserId());
		}

		return orgEntity;
		
	}
	
	
	/**
	 * @param orgEntity
	 * @return
	 */
	private Organization populateOrgModel(OrgEntity orgEntity){

		Organization org = new Organization();
		if (orgEntity != null){
			org.setOrgId(orgEntity.getOrgId());
			org.setOrgName(orgEntity.getOrgName());
			org.setOrgType(orgEntity.getOrgType());
		}
		return org;
	}
	
	
	/**
	 * @param orgs
	 * @return
	 */
	private List<Organization> pupulateModelList(List<OrgEntity> orgs){
		
		List<Organization> orgList = new ArrayList<Organization>();
		if (orgs != null && !orgs.isEmpty()){
			for (OrgEntity org : orgs){
				orgList.add(populateOrgModel(org));
			}
		}
		return orgList;
	}

}
