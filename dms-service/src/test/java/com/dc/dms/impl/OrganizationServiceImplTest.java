package com.dc.dms.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DMSServiceConfig.class, DatabaseJpaConfig.class })
public class OrganizationServiceImplTest {

	private static String ORG_ID = "1";
	private static String ORG_USER_ID = "1";
	
	
	@InjectMocks
	@Autowired
	OrganizationService orgService = null;
	
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
			
			org = orgService.getOrganizationDetail(new BigInteger(ORG_ID));
			
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
			
			Organization updatedOrg = orgService.upsertOrginzation(orgToBeCreated());
			
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
			
			List<Organization> orgList = orgService.getUserOrganizations(new BigInteger(ORG_USER_ID));
			
			assertThat(orgList, is(not(orgList.isEmpty())));
		} catch (DMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private OrgEntity returnAlreadyPresentOrg(){
		OrgEntity orgEntity = new OrgEntity();
		
		orgEntity.setOrgId(new BigInteger(ORG_ID));
		orgEntity.setOrgName("Auto IMS India");
		orgEntity.setOrgType("Auto DealrerShip");
		orgEntity.setUserId(new BigInteger(ORG_USER_ID));
		
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
		org.setUserId(new BigInteger(ORG_USER_ID));
		
		return org;
		
	}
	
	private Organization orgToBeUpdated(){
		Organization org = new Organization();
		
		org.setOrgId(new BigInteger(ORG_ID));
		org.setOrgName("Auto IMS India updated");
		org.setOrgType("Auto Dealership");
		org.setUserId(new BigInteger(ORG_USER_ID));
		
		return org;
	}
	

}