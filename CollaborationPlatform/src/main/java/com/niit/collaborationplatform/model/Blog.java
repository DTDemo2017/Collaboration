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
@Component
public class Blog extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 10L;

	
	/**
		 *  declare the database column names for Blog... 
		 */
	@Id
	
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_BLOG_ID", allocationSize=1)
		
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
		
	private int id;
		
		
	private String title;
		
		
	
		
		
	private String content;
	
	private String userId;
		
		
	//@Column(name = "post_date")
	@JsonFormat(pattern="yyyy-MM-dd")	
	private Date postDate;
		
	private String status;
	
	private int countLike;
	
	private String userName;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public int getCountLike() {
		return countLike;
	}

	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
    
	

}
