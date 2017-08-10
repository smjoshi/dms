package com.dc.dms.intf;

import java.math.BigInteger;
import java.util.List;

import com.dc.dms.domain.model.ProductDocDetail;
import com.dc.dms.domain.model.ProductDocument;
import com.dc.dms.exception.DMSException;

/**
 * @author sacjoshi
 *
 */
public interface ProductDocumentService {

	public ProductDocument upsertProductDocuments(ProductDocument pdd) throws DMSException;

	public List<ProductDocument> getProductDocuments(BigInteger productId) throws DMSException;


	public List<ProductDocDetail> getProductDocuments(Integer customerId, Integer productId) throws DMSException;

}
