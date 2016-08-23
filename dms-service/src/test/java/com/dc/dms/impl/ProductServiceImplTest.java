/**
 * 
 */
package com.dc.dms.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.ProductDao;
import com.dc.dms.domain.model.Product;
import com.dc.dms.entity.ProductEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductService;
import static org.mockito.Mockito.*;

/**
 * @author sjoshi
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, DatabaseJpaConfig.class })
public class ProductServiceImplTest {

	private String ORG_ID = "1";
	private String PRODUCT_ID = "1";

	@InjectMocks
	@Autowired
	private ProductService productService = null;

	@Mock
	private ProductDao productDao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link com.dc.dms.impl.ProductServiceImpl#upsertProduct(com.dc.dms.domain.model.Product)}
	 * .
	 */
	@Test
	public void testUpsertProduct_insert_with_noexception() {

		try {
			when(productDao.create(any(ProductEntity.class))).thenReturn(productEntityDBInstance());
			Product createdProduct = productService.upsertProduct(productToBeCreated());

			assertEquals(new BigInteger(PRODUCT_ID), createdProduct.getProductId());
		} catch (DMSDaoException e) {

			e.printStackTrace();
		} catch (DMSException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testUpsertProduct_update_with_noexception() {

		try {
			when(productDao.update(any(ProductEntity.class))).thenReturn(true);
			Product updatedProduct = productService.upsertProduct(productToBeUpdated());

			assertEquals("Passenger Cars / Family Car", updatedProduct.getDescription());
		} catch (DMSDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.dc.dms.impl.ProductServiceImpl#getProductsByOrg(java.math.BigInteger)}
	 * .
	 */
	@Test
	public void testGetProductsByOrg() {
		try {
			when(productDao.getOrgProducts(any(BigInteger.class))).thenReturn(getOrgProductEntity());
			List<Product> productList = productService.getProductsByOrg(new BigInteger(ORG_ID));
			assertThat(productList, is(not(productList.isEmpty())));
		} catch (DMSException e) {

			e.printStackTrace();
		}
	}

	private Product productToBeCreated() {
		Product product = new Product();
		product.setDescription("Passenger Car");
		product.setOrgId(new BigInteger(ORG_ID));
		product.setProductCode("010101");
		// product.setProductId(new BigInteger(PRODUCT_ID));
		product.setProductName("Passenger Car");

		return product;
	}

	private Product productToBeUpdated() {
		Product product = new Product();
		product.setDescription("Passenger Cars / Family Car");
		product.setOrgId(new BigInteger(ORG_ID));
		product.setProductCode("010101");
		product.setProductId(new BigInteger(PRODUCT_ID));
		product.setProductName("Passenger Car");

		return product;
	}

	private List<ProductEntity> getOrgProductEntity() {

		List<ProductEntity> productList = new ArrayList<>();
		ProductEntity product = new ProductEntity();
		product.setProductDesc("Passenger Cars / Family Car");
		product.setOrgId(new BigInteger(ORG_ID));
		product.setProductCode("010101");
		product.setProductId(new BigInteger(PRODUCT_ID));
		product.setProductName("Passenger Car");
		productList.add(product);

		return productList;
	}

	private ProductEntity productEntityDBInstance() {

		ProductEntity product = new ProductEntity();
		product.setProductDesc("Passenger Cars / Family Car");
		product.setOrgId(new BigInteger(ORG_ID));
		product.setProductCode("010101");
		product.setProductId(new BigInteger(PRODUCT_ID));
		product.setProductName("Passenger Car");

		return product;

	}

}
