package com.dc.dms.dao.intf;

import java.math.BigInteger;

import java.util.List;

import com.dc.dms.base.BaseDao;
import com.dc.dms.entity.OrgEntity;

public interface OrganizationDao extends BaseDao<OrgEntity> {

	public List<OrgEntity> getUserOrgnizations(BigInteger userId);
}
