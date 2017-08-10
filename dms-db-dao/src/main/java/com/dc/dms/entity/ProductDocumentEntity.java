package com.dc.dms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * The persistent class for the product_doc_details database table.
 * 
 */
@Entity
@Table(name = "PRODUCT_DOCUMENTS")
@NamedQuery(name = "ProductDocumentEntity.findAll", query = "SELECT p FROM ProductDocumentEntity p")
public class ProductDocumentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PRODUCT_DOC_ID")
	private BigInteger productDocumentId;

	@Column(name = "URL")
	private String docUrl;

	@Column(name = "PRODUCT_DOC_CONF_ID")
	private BigInteger productDocConfId;

	@Column(name = "PRODUCT_ID")
	private BigInteger productId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_DOC_CONF_ID", insertable=false, updatable=false)
	private ProductDocConfEntity docConfiguration; 

	public ProductDocumentEntity() {
	}

	public BigInteger getProductDocumentId() {
		return this.productDocumentId;
	}

	public void setProductDocumentId(BigInteger productDocumentId) {
		this.productDocumentId = productDocumentId;
	}

	public String getDocUrl() {
		return this.docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public BigInteger getProductDocConfId() {
		return this.productDocConfId;
	}

	public void setProductDocConfId(BigInteger productDocConfId) {
		this.productDocConfId = productDocConfId;
	}

	public BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public ProductDocConfEntity getDocConfiguration() {
		return docConfiguration;
	}

	public void setDocConfiguration(ProductDocConfEntity docConfiguration) {
		this.docConfiguration = docConfiguration;
	}
	
	

}