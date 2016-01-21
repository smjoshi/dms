package com.dc.dms.domain.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1877464203488457714L;
	
	@XmlElement(name="productId")
	protected BigInteger productId;
	
	
	@XmlElement(name="productName")
	protected String productName;
	
	@XmlElement(name="description")
	private String  description;
	
	@XmlElement(name="productCode")
	protected String productCode;
	
	@XmlElement(name="orgId")
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
