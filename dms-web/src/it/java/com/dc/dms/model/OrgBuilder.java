package com.dc.dms.model;

import com.dc.dms.domain.model.Organization;

import java.math.BigInteger;

/**
 * Created by sacjoshi on 12/22/2016.
 */
public class OrgBuilder {

    private Long orgId = null;
    private String orgName = "Test Org Name";
    private String orgType = "Test Org Type";
    private Long userId = new Long(1L);

    public OrgBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public OrgBuilder withOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public OrgBuilder withOrgType(String orgType) {
        this.orgType = orgType;
        return this;
    }

    public OrgBuilder withOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public Organization build() {
        Organization org = new Organization();

        org.setOrgType(this.orgType);
        org.setOrgName(this.orgName);
        org.setUserId(new BigInteger(this.userId.toString()));
        if (this.orgId != null) {
            org.setOrgId(new BigInteger(this.orgId.toString()));
        }
        return org;
    }
}
