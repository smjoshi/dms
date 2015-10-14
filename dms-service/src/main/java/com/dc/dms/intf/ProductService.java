package com.dc.dms.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.domain.model.Product;
import com.dc.dms.exception.DMSException;

public interface ProductService {
	
	public Product createOrUpdateProduct(Product product) throws DMSException;
	
	public List<Product> getProductsByOrg(BigInteger orgId) throws DMSException;
	

}
