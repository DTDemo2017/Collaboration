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

//@Table(name = "B_FORUMCOMMENT")

@Component
public class ForumComment extends BaseDomain implements Serializable{
private static final long serialVersionUID = 10L;
	
	/**
	 *  declare the database column names for ForumComment... 
	 */
@Id

@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FORUMCOMMENT_ID", allocationSize=1)
	
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	
private int id;

private int forumId;

private String comments;

private String userId;

@JsonFormat(pattern="yyyy-MM-dd")
private Date commentDate;

private String forumName;

private String userName;




public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getForumId() {
	return forumId;
}

public void setForumId(int forumId) {
	this.forumId = forumId;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public Date getCommentDate() {
	return commentDate;
}

public void setCommentDate(Date commentDate) {
	this.commentDate = commentDate;
}




public String getForumName() {
	return forumName;
}

public void setForumName(String forumName) {
	this.forumName = forumName;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}




}
