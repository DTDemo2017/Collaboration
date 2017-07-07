package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.JobApplication;
@Repository
public interface JobApplicationDAO {
	public boolean applyForJob(JobApplication jobApplication);
	
	public boolean updateJobApplication(JobApplication jobApplication);

	public JobApplication get(int id);

	public void update(JobApplication jobApplication1);
	
	public List<JobApplication> getMyAppliedJobs(String userId);
	
	public JobApplication get(String userId, String jobId);
	
	public boolean callForInterview(JobApplication jobapplication);
	
	public boolean rejectJobApplication(JobApplication jobapplication);
	
	

	

}
