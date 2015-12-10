/**
 * 
 */
package com.dc.dms.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

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
import com.dc.dms.dao.intf.ProductDocConfDao;
import com.dc.dms.domain.model.ProductDocConfiguration;
import com.dc.dms.entity.ProductDocConfEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.ProductDocConfService;

import static org.mockito.Mockito.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sjoshi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, DatabaseJpaConfig.class })
public class ProductDocConfServiceImplTest {

	private String CONF_ID = "1";
	private String PRODUCT_ID = "1";

	@InjectMocks
	@Autowired
	ProductDocConfService docConfService = null;

	@Mock
	ProductDocConfDao confDao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link com.dc.dms.impl.ProductDocConfServiceImpl#upsertProductDocConfiguration(com.dc.dms.domain.model.ProductDocConfiguration)}
	 * .
	 */
	@Test
	public void testUpsertProductDocConfiguration_insert_withnoexception() {

		try {
			when(confDao.create(any(ProductDocConfEntity.class))).thenReturn(createdProductDocConf());
			ProductDocConfiguration createdConf = docConfService.upsertProductDocConfiguration(confToBeCreated());

			assertEquals(new BigInteger(CONF_ID), createdConf.getDocConfId());
		} catch (DMSDaoException e) {
			e.printStackTrace();
		} catch (DMSException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.dc.dms.impl.ProductDocConfServiceImpl#upsertProductDocConfiguration(com.dc.dms.domain.model.ProductDocConfiguration)}
	 * .
	 */
	@Test
	public void testUpsertProductDocConfiguration_update_withnoexception() {
		try {
			when(confDao.create(any(ProductDocConfEntity.class))).thenReturn(createdProductDocConf());
			ProductDocConfiguration updatedConf = docConfService.upsertProductDocConfiguration(confToBeUpdated());

			assertEquals("Passenger Vehicle Front Image", updatedConf.getDescription());
		} catch (DMSDaoException e) {
			e.printStackTrace();
		} catch (DMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.dc.dms.impl.ProductDocConfServiceImpl#getProductDocConfiguration(java.math.BigInteger)}
	 * .
	 */
	@Test
	public void testGetProductDocConfiguration() {
		try {
			when(confDao.getProductDocConfigurations(any(BigInteger.class))).thenReturn(getDocConfigrations());
			List<ProductDocConfiguration> confList = docConfService
					.getProductDocConfigurations(new BigInteger(PRODUCT_ID));

			assertThat(confList, is(not(confList.isEmpty())));
		} catch (DMSException e) {
			e.printStackTrace();
		}

	}

	private ProductDocConfEntity createdProductDocConf() {
		ProductDocConfEntity confEntity = new ProductDocConfEntity();
		confEntity.setDescription("Front Image");
		confEntity.setDocTypeCode("Image");
		confEntity.setGrouPId(null);
		confEntity.setMandatory(true);
		confEntity.setMultipleItemAllowed(true);
		confEntity.setProductDocConfId(new BigInteger(CONF_ID));
		confEntity.setProductId(new BigInteger(PRODUCT_ID));

		return confEntity;
	}

	private ProductDocConfiguration confToBeCreated() {

		ProductDocConfiguration conf = new ProductDocConfiguration();
		conf.setDescription("Front Image");
		conf.setDocTypeCode("Image");
		conf.setGroupId(null);
		conf.setMandatory(true);
		conf.setMultipleItemAllowed(true);

		conf.setProductId(new BigInteger(PRODUCT_ID));

		return conf;

	}

	private ProductDocConfiguration confToBeUpdated() {

		ProductDocConfiguration conf = new ProductDocConfiguration();
		conf.setDescription("Passenger Vehicle Front Image");
		conf.setDocTypeCode("Image");
		conf.setGroupId(null);
		conf.setMandatory(true);
		conf.setMultipleItemAllowed(false);
		conf.setDocConfId(new BigInteger(CONF_ID));
		conf.setProductId(new BigInteger(PRODUCT_ID));

		return conf;

	}

	private List<ProductDocConfEntity> getDocConfigrations() {
		List<ProductDocConfEntity> confs = new ArrayList<>();

		ProductDocConfEntity confEntity = new ProductDocConfEntity();
		confEntity.setDescription("Front Image");
		confEntity.setDocTypeCode("Image");
		confEntity.setGrouPId(null);
		confEntity.setMandatory(true);
		confEntity.setMultipleItemAllowed(true);
		confEntity.setProductDocConfId(new BigInteger(CONF_ID));
		confEntity.setProductId(new BigInteger(PRODUCT_ID));

		confs.add(confEntity);
		return confs;

	}

}
