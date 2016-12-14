package com.dc.dms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "product_doc_conf")
@NamedQuery(name = "ProductDocConfEntity.findAll", query = "SELECT pdc FROM ProductDocConfEntity pdc")
public class ProductDocConfEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PR_DOC_CONF_ID")
    private BigInteger productDocConfId;

    @Column(name = "PRODUCT_ID")
    private BigInteger productId;

    @Column(name = "DOC_TYPE_CODE")
    private String docTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_MANDATORY")
    private boolean isMandatory;

    @Column(name = "IS_MULTIPLE_ITEM_ALLOWED")
    private boolean isMultipleItemAllowed;

    @Column(name = "GROUP_ID")
    private String grouPId;

    @OneToOne(mappedBy = "docConfiguration")
    private ProductDocDetailEntity docDetail;

    public BigInteger getProductDocConfId() {
        return productDocConfId;
    }

    public void setProductDocConfId(BigInteger productDocConfId) {
        this.productDocConfId = productDocConfId;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public boolean isMultipleItemAllowed() {
        return isMultipleItemAllowed;
    }

    public void setMultipleItemAllowed(boolean isMultipleItemAllowed) {
        this.isMultipleItemAllowed = isMultipleItemAllowed;
    }

    public String getGrouPId() {
        return grouPId;
    }

    public void setGrouPId(String grouPId) {
        this.grouPId = grouPId;
    }

    public ProductDocDetailEntity getDocDetail() {
        return docDetail;
    }

    public void setDocDetail(ProductDocDetailEntity docDetail) {
        this.docDetail = docDetail;
    }

}
