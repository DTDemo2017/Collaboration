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

//@Table(name = "B_BLOGCOMMENT")

@Component
public class BlogComment extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 10L;
	
	/**
	 *  declare the database column names for BlogComment... 
	 */
@Id

@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_BLOGCOMMENT_ID", allocationSize=1)
	
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	
private int id;

private int blogId;

private String comments;

@JsonFormat(pattern="yyyy-MM-dd")
private Date commentDate;

private String userId;

private String userName;

private String blogName;




public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getBlogId() {
	return blogId;
}

public void setBlogId(int blogId) {
	this.blogId = blogId;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public Date getCommentDate() {
	return commentDate;
}

public void setCommentDate(Date commentDate) {
	this.commentDate = commentDate;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}



public String getBlogName() {
	return blogName;
}

public void setBlogName(String blogName) {
	this.blogName = blogName;
}






}
