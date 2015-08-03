package com.dc.dms.domain.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable{
	
	private static final long serialVersionUID = 1808577401503975054L;
		
	@XmlElement(name="email")
	private String email;
	
	@XmlElement(name="firstName")
	private String firstName;
	
	@XmlElement(name="lastName")
	private String lastName;
	
	@XmlElement(name="loginId")
	private String loginId;
	
	@XmlElement(name="orgName")
	private String orgName;
	
	@XmlElement(name="password")
	private String password;
	
	@XmlElement(name="userId")
	private BigInteger userId;
	
	
	/**
	 * Default constructor.
	 */
	public User(){
		
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	
	
	

}
