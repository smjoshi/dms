package com.dc.dms.model;

import com.dc.dms.domain.model.Product;

import java.math.BigInteger;

/**
 * Created by sacjoshi on 1/2/2017.
 */
public class ProductBuilder {

    private Integer productId = null;
    private Integer orgId = null;
    private String productName = "Product Name";
    private String description = "Product Desc";
    private String productCode = "PRODUCTCODE01";


    public ProductBuilder withOrgId(Integer orgId){
        this.orgId = orgId;
        return this;
    }

    public ProductBuilder withProductName(String productName){
        this.productName = productName;
        return this;
    }

    public ProductBuilder withDescription(String desciption){
        this.description = desciption;
        return this;
    }

    public ProductBuilder withProductCode(String productCode){
        this.productCode = productCode;
        return this;
    }


    public Product build(){
        Product product = new Product();

        if (this.productId != null){
            product.setProductId(new BigInteger(this.productId.toString()));
        }

        if (this.orgId != null){
            product.setOrgId(new BigInteger(this.orgId.toString()));
        }

        product.setProductName(this.productName);
        product.setDescription(this.description);
        product.setProductCode(this.productCode);

        return product;
    }

}
