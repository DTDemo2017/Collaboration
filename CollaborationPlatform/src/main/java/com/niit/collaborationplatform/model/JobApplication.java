package com.niit.collaborationplatform.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Entity
@Component
public class JobApplication extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 10L;
	
	
    @Id
	
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_JOBAPPLICATION_ID", allocationSize=1)
		
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
		
	private int id;
	
	private String userId;
	
	private int jobId;
	
	private String status;
	
	private String remarks;

	
	
	
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

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
