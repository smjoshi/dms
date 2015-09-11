package com.dc.dms.domain.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
	
	@XmlElementRef(name="orgId")
	public Long orgId;
	
	@XmlElementRef(name="orgName")
	public String orgName;
	
	@XmlElementRef(name="orgType")
	public String orgType;
	
	@XmlElementRef(name="userId")
	public Long userId;
	
	
	
	
	
	
	

}
