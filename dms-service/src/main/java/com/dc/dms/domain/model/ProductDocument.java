package com.dc.dms.domain.model;


import com.dc.dms.entity.ProductDocumentEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author sacjoshi
 */
@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDocument implements Serializable {

    private BigInteger productDocumentId;
    private BigInteger productId;
    private BigInteger productDocConfId;

    private String docUrl;

    private ProductDocConfiguration docConfiguration;

    public BigInteger getProductDocumentId() {
        return productDocumentId;
    }

    public void setProductDocumentId(BigInteger productDocDetailId) {
        this.productDocumentId = productDocDetailId;
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

    public void copyFromEntity(ProductDocumentEntity pde) {

        if (pde != null){
            this.setProductId(pde.getProductId());
            this.setProductDocumentId(pde.getProductDocumentId());
            this.setProductDocConfId(pde.getProductDocConfId());
            this.setDocUrl(pde.getDocUrl());
        }
    }

}
