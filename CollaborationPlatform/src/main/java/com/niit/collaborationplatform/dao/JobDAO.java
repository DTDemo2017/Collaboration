package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.Job;
import com.niit.collaborationplatform.model.JobApplication;

@Repository
public interface JobDAO {
	
	public boolean save(Job job);
	
	
	public boolean update(Job job);
		
		
	public boolean saveOrUpdate(Job job);
		
		
	public boolean delete(Job job);
		
		
	public Job get(int id);
		
		
	public List<Job> list();
	
	
	public List<Job> listVacantJobs();
	
	
	
	
	public List<JobApplication> listJobApplications();
	
	
	//public boolean applyForJob(JobApplication jobApplication);			 //implemented..	
	//public boolean updateJobApplication(JobApplication jobApplication);	                    //implemented..	
	
	//public JobApplication get(String userId, String jobId);		//JobApplicationDAO		//implemented..	
	//public List<Job> getMyAppliedJobs(String userId);				//In JobApplicationDAO

}
