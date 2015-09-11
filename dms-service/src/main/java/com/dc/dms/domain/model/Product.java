package com.dc.dms.domain.model;

import java.io.Serializable;

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
	public Long productId;
	
	
	@XmlElementRef(name="productName")
	public String productName;
	
	@XmlElementRef(name="description")
	public String  description;
	
	@XmlElementRef(name="productCode")
	public String productCode;
	
	@XmlElementRef(name="orgId")
	public Long orgId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	
	

}
