package com.dc.dms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;


@Entity
@Table(name = "product")
@NamedQuery(name = "ProductEntity.findAll", query = "SELECT p FROM ProductEntity p")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private BigInteger productId;

    @Column(name = "ORGANIZATION_ID")
    private BigInteger orgId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String productDesc;

    @Column(name = "PRODUCT_CODE")
    private String productCode;


    @OneToMany
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private List<ProductDocConfEntity> docConfigurations;


    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<ProductDocConfEntity> getDocConfigurations() {
        return docConfigurations;
    }

    public void setDocConfigurations(List<ProductDocConfEntity> docConfigurations) {
        this.docConfigurations = docConfigurations;
    }


}
