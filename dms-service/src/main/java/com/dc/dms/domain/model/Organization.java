package com.dc.dms.domain.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -505445923066279215L;
	
	@XmlElement(name="orgId")
	public BigInteger orgId;
	
	@XmlElement(name="orgName")
	public String orgName;
	
	@XmlElement(name="orgType")
	public String orgType;
	
	@XmlElement(name="userId")
	public BigInteger userId;

	public BigInteger getOrgId() {
		return orgId;
	}

	public void setOrgId(BigInteger orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	
	
	
	
	
	
	

}
