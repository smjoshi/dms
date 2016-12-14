package com.dc.dms.domain.model;

import com.dc.dms.entity.ProductDocConfEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDocConfiguration implements Serializable {

    protected BigInteger productId;
    protected BigInteger docConfId;
    protected String docTypeCode;
    protected boolean isMandatory;
    protected boolean isMultipleItemAllowed;
    protected String groupId;
    protected String description;

    protected ProductDocument productDocument;

    public ProductDocument getProductDocument() {
        return productDocument;
    }

    public void setProductDocument(ProductDocument productDocument) {
        this.productDocument = productDocument;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public BigInteger getDocConfId() {
        return docConfId;
    }

    public void setDocConfId(BigInteger docConfId) {
        this.docConfId = docConfId;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void copyFromEntiry(ProductDocConfEntity entity) {
        this.productId = entity.getProductId();
        this.docConfId = entity.getProductDocConfId();
        this.description = entity.getDescription();
        this.groupId = entity.getGrouPId();
        this.setMandatory(entity.isMandatory());
        this.setMultipleItemAllowed(entity.isMultipleItemAllowed());
        this.setDocTypeCode(entity.getDocTypeCode());
    }

}
