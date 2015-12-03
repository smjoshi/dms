package com.dc.dms.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.domain.model.Organization;
import com.dc.dms.exception.DMSException;

public interface OrganizationService {
	
	public Organization upsertOrginzation(Organization org) throws DMSException;
	
	public List<Organization> getUserOrganizations(BigInteger userId) throws DMSException;
	
	public Organization getOrganizationDetail(BigInteger orgId) throws DMSException;

}
