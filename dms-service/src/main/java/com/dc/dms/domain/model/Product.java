package com.dc.dms.domain.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1877464203488457714L;
	
	@XmlElementRef(name="productId")
	protected BigInteger productId;
	
	
	@XmlElementRef(name="productName")
	protected String productName;
	
	@XmlElementRef(name="description")
	private String  description;
	
	@XmlElementRef(name="productCode")
	protected String productCode;
	
	@XmlElementRef(name="orgId")
	protected BigInteger orgId;

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigInteger getOrgId() {
		return orgId;
	}

	public void setOrgId(BigInteger orgId) {
		this.orgId = orgId;
	}
	
	
	

}
