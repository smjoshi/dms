package com.dc.dms.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.dc.dms.config.TestDatabaseJpaConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dc.dms.config.DMSServiceConfig;
import com.dc.dms.config.DatabaseJpaConfig;
import com.dc.dms.dao.exception.DMSDaoException;
import com.dc.dms.dao.intf.OrganizationDao;
import com.dc.dms.domain.model.Organization;
import com.dc.dms.entity.OrgEntity;
import com.dc.dms.exception.DMSException;
import com.dc.dms.intf.OrganizationService;



@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, TestDatabaseJpaConfig.class })
public class OrganizationServiceImplTest {

	private static BigInteger ORG_ID = new BigInteger("1");
	private static BigInteger ORG_USER_ID =  new BigInteger("1");
	
	
	@InjectMocks
	OrganizationService orgService = new OrganizationServiceImpl();
	
	@Mock
	OrganizationDao orgDao = null;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetOrganizationDetail() {
		
		Organization org;
		try {
			when(orgDao.readByKey(Matchers.any(OrgEntity.class))).thenReturn(returnAlreadyPresentOrg());
			org = orgService.getOrganizationDetail(ORG_ID);
			
			assertEquals(ORG_ID, org.getOrgId());
		} catch (DMSDaoException e) {
			e.printStackTrace();
		} catch (DMSException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testUpsertOrginzation_with_Insert_noexception() {
		
		try {
			when(orgDao.create(Matchers.any(OrgEntity.class))).thenReturn(returnAlreadyPresentOrg());
			Organization createdOrg = orgService.upsertOrginzation(orgToBeCreated());
			
			assertEquals(ORG_ID, createdOrg.getOrgId());
		} catch (DMSDaoException e) {
			e.printStackTrace();
		} catch (DMSException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpsertOrginzation_with_update_noexception() {
		
		try {
			when(orgDao.update(Matchers.any(OrgEntity.class))).thenReturn(true);
			Organization updatedOrg = orgService.upsertOrginzation(orgToBeUpdated());
			
			assertEquals("Auto IMS India updated", updatedOrg.getOrgName());
		} catch (DMSDaoException e) {
			e.printStackTrace();
		} catch (DMSException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserOrganizations() {
		
		try {
			when(orgDao.getUserOrgnizations(Matchers.any(BigInteger.class))).thenReturn(returnUsersOgnizations());
			List<Organization> orgList = orgService.getUserOrganizations(ORG_USER_ID);
			
			assertThat(orgList, is(not(orgList.isEmpty())));
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private OrgEntity returnAlreadyPresentOrg(){
		OrgEntity orgEntity = new OrgEntity();
		
		orgEntity.setOrgId(ORG_ID);
		orgEntity.setOrgName("Auto IMS India");
		orgEntity.setOrgType("Auto DealrerShip");
		orgEntity.setUserId(ORG_USER_ID);
		
		return orgEntity;
		
	}
	
	
	private List<OrgEntity> returnUsersOgnizations(){
		List<OrgEntity> userOrgs = new ArrayList<>();
		userOrgs.add(returnAlreadyPresentOrg());
		
		return userOrgs;
	}
	
	private Organization orgToBeCreated(){
		Organization org = new Organization();
		
		org.setOrgName("Auto IMS India");
		org.setOrgType("Auto Dealership");
		org.setUserId(ORG_USER_ID);
		
		return org;
		
	}
	
	private Organization orgToBeUpdated(){
		Organization org = new Organization();
		
		org.setOrgId(ORG_ID);
		org.setOrgName("Auto IMS India updated");
		org.setOrgType("Auto Dealership");
		org.setUserId(ORG_USER_ID);
		
		return org;
	}
	

}
