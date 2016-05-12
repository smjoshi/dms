package com.dc.dms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;

/**
 * The persistent class for the product_doc_details database table.
 * 
 */
@Entity
@Table(name = "product_doc_details")
@NamedQuery(name = "ProductDocDetailEntity.findAll", query = "SELECT p FROM ProductDocDetailEntity p")
public class ProductDocDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_doc_detail_id")
	private BigInteger productDocDetailId;

	@Column(name = "doc_url")
	private String docUrl;

	@Column(name = "product_doc_conf_id")
	private BigInteger productDocConfId;

	@Column(name = "product_id")
	private BigInteger productId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_doc_conf_id", insertable=false, updatable=false)
	private ProductDocConfEntity docConfiguration; 

	public ProductDocDetailEntity() {
	}

	public BigInteger getProductDocDetailId() {
		return this.productDocDetailId;
	}

	public void setProductDocDetailId(BigInteger productDocDetailId) {
		this.productDocDetailId = productDocDetailId;
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