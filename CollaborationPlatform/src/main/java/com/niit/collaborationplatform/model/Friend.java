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

//@Table(name = "B_FRIEND")

@Component
public class Friend extends BaseDomain implements Serializable {
	
private static final long serialVersionUID = 10L;

	
	/**
		 *  declare the database column names for Friend... 
		 */
	@Id
	
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FRIEND_ID", allocationSize=1)
		
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
		
	private int id;
	
	private String userId;
	
	private String friendId;
	
	private String status;
	
	private String isOnline;
	
	private String userName;
	
	private String friendName;
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date friendDate;

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
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

	
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	
	
	
	public Date getFriendDate() {
		return friendDate;
	}

	public void setFriendDate(Date friendDate) {
		this.friendDate = friendDate;
	}
	
	
	
	

}
