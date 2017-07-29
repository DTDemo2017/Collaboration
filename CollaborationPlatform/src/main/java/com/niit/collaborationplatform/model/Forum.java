package com.niit.collaborationplatform.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

//@Table(name = "B_FORUM")

@Component
public class Forum extends BaseDomain implements Serializable {
	
private static final long serialVersionUID = 10L;

	
	/**
		 *  declare the database column names for Forum... 
		 */
	@Id
	
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FORUM_ID", allocationSize=1)
		
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
		
	private int id;
	
	private String description;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date postDate;
	
	private String userId;
	
	private String status;
	
	private String userName;
	

	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	

}
