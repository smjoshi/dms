package com.dc.dms.model;

import com.dc.dms.domain.model.ProductDocConfiguration;

import java.math.BigInteger;

/**
 * Created by sacjoshi on 1/3/2017.
 */
public class ProductDocConfigurationBuilder {

    protected Integer productId = null;
    protected Integer docConfId = null;
    protected String docTypeCode = "FRONT IMAGE";
    protected boolean isMandatory = false;
    protected boolean isMultipleItemAllowed = false;
    protected String groupId = null;
    protected String description = " Configuration description";

    public ProductDocConfigurationBuilder  withProductId(Integer productId){
        this.productId = productId;
        return this;
    }

    public ProductDocConfigurationBuilder withDocConfigurationId(Integer configId){
        this.docConfId = configId;
        return this;
    }

    public ProductDocConfigurationBuilder withDocTypeCode(String docTypeCode){
        this.docTypeCode = docTypeCode;
        return this;
    }

    public ProductDocConfigurationBuilder withIsMandatory(boolean isMandatory){
        this.isMandatory = isMandatory;
        return this;
    }

    public ProductDocConfigurationBuilder withIsMultipleItemAllowed(boolean isMultipleItemAllowed){
        this.isMultipleItemAllowed = isMultipleItemAllowed;
        return this;
    }

    public ProductDocConfigurationBuilder withGroupId(String groupId){
        this.groupId = groupId;
        return this;
    }

    public ProductDocConfigurationBuilder withDescription(String description){
        this.description = description;
        return this;
    }


    public ProductDocConfiguration build(){
        ProductDocConfiguration configuration = new ProductDocConfiguration();

        if (this.productId != null){
            configuration.setProductId(new BigInteger(productId.toString()));
        }

        if (this.docConfId != null){
            configuration.setDocConfId(new BigInteger(this.docConfId.toString()));
        }

        configuration.setDocTypeCode(this.docTypeCode);
        configuration.setGroupId(this.groupId);
        configuration.setMandatory(this.isMandatory);
        configuration.setDescription(this.description);
        configuration.setMultipleItemAllowed(this.isMultipleItemAllowed);

        return configuration;
    }


}
