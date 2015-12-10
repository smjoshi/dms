package com.dc.dms.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.exception.DMSException;

/**
 * @author SJoshi
 *
 */
public interface ProductDocConfService {
	
	public ProductDocConfiguration upsertProductDocConfiguration(ProductDocConfiguration pdc) throws DMSException;
	
	public List<ProductDocConfiguration> getProductDocConfigurations(BigInteger productId) throws DMSException;
	

}
