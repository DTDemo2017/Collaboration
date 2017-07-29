package com.niit.collaborationplatform.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

@Entity

@Table(name = "USERS")

@Component
public class Users extends BaseDomain implements Serializable {
private static final long serialVersionUID = 10L;

	
	/**
		 *  declare the database column names for Users... 
		 */
	@Id
	
	private String id;
	
	private String name;
	
	private String password;
	
	@Email
	private String email;
	
	private String role;
	
	private String status;
	
	private String isOnline;
	
	

	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	
	
	
	

}
