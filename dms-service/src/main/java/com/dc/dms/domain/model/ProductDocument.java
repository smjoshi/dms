package com.dc.dms.domain.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sacjoshi
 *
 */
@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDocument implements Serializable {

	private BigInteger productDocDetailId;
	private BigInteger productId;
	private BigInteger productDocConfId;
	private String docUrl;
	
	private ProductDocConfiguration docConfiguration;

	public BigInteger getProductDocDetailId() {
		return productDocDetailId;
	}

	public void setProductDocDetailId(BigInteger productDocDetailId) {
		this.productDocDetailId = productDocDetailId;
	}

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public BigInteger getProductDocConfId() {
		return productDocConfId;
	}

	public void setProductDocConfId(BigInteger productDocConfId) {
		this.productDocConfId = productDocConfId;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public ProductDocConfiguration getDocConfiguration() {
		return docConfiguration;
	}

	public void setDocConfiguration(ProductDocConfiguration docConfiguration) {
		this.docConfiguration = docConfiguration;
	}

}
