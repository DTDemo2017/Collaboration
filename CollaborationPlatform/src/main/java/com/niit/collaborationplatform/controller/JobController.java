package com.niit.collaborationplatform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationplatform.dao.JobApplicationDAO;
import com.niit.collaborationplatform.dao.JobDAO;
import com.niit.collaborationplatform.model.Job;
import com.niit.collaborationplatform.model.JobApplication;
import com.niit.collaborationplatform.model.Users;

@RestController
public class JobController {
	
	Logger log = Logger.getLogger(JobController.class);
	
	@Autowired
	Job job;
	
	@Autowired
	JobDAO jobDAO;

	@Autowired
	JobApplication jobApplication;
	
	@Autowired
	JobApplicationDAO jobApplicationDAO;
	
	
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		log.debug("**********Starting of listJobs() method.");
		List<Job> job = jobDAO.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listJobs() method.");
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}



@PostMapping(value = "/job/")
public ResponseEntity<Job> saveJob(@RequestBody Job job, HttpSession session) {
	log.debug("**********Starting of saveJob() method.");
	Users loggedInUser = (Users)session.getAttribute("loggedInUser");
	{
		
		//job.setId(2);
		//job.setCompanyName("Wipro ");//
		//job.setLocation("Salt Lake SectorV");//
		//job.setDescription("BPO for British Telecom ");//
		job.setJobDate(new Date());
		job.setStatus("V");
		job.setPosts(400);
		
		jobDAO.save(job);
		log.debug("**********End of saveJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
		
	}
}
	
	@PutMapping(value = "/updateJob/{id}")   // in URL we give/updateJob/1
	public ResponseEntity<Job> updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		log.debug("**********Starting of updateJob() method.");
		
		{
				Job job1=jobDAO.get(id);
				job1.setStatus(job.getStatus());
				jobDAO.update(job1);
				log.debug("**********End of updateJob() method.");
			return new ResponseEntity<Job>(job1, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping(value = "/deleteJob/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteJob() method.");
		Job job = jobDAO.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDAO.delete(job);
		log.debug("**********End of deleteJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);		
	}
	
	
	@GetMapping(value = "/getJob/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") int id) {
		log.debug("**********Starting of getJob() method.");
		Job job = jobDAO.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/listVacantJobs")
	public ResponseEntity<List<Job>> listVacantJobs() {
		log.debug("**********Starting of listVacantJobs() method.");
		List<Job> vacantJobs = jobDAO.listVacantJobs();
		if (vacantJobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listVacantJobs() method.");
		return new ResponseEntity<List<Job>>(vacantJobs, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/jobApplication")
	public ResponseEntity<JobApplication> applyForJob(@RequestBody Job job, HttpSession httpSession) {		
		log.debug("**********Starting of applyForJob() method.");
     {
    	 
		Users loggedInUser = (Users)httpSession.getAttribute("loggedInUser");
		jobApplication.setUserId(loggedInUser.getId());
                                       //jobApplication.setUserId("U1");
		//jobApplication.setJobId("J2");
		jobApplication.setJobId(job.getId());
		jobApplication.setStatus("A"); // A = Applied ||R = Rejected ||C = Call for Interview 

		jobApplicationDAO.applyForJob(jobApplication);

		log.debug("**********End of applyForJob() method.");
		return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
	}
}
	
	
	@GetMapping(value = "/jobApplications")
	public ResponseEntity<List<JobApplication>> listJobApplications() {
		log.debug("**********Starting of listJobApplications() method.");
		
		List<JobApplication> jobApplications = jobDAO.listJobApplications();
		if (jobApplications.isEmpty()) {
			return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
		}
		
		log.debug("**********End of listJobApplications() method.");
		return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getMyAppliedJobs")
	public ResponseEntity<List<JobApplication>> getMyAppliedJobs(HttpSession httpSession) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		String userId = (String)httpSession.getAttribute("loggedInUserID");
		//Users loggedInUser = (Users) httpSession.getAttribute("loggedInUser");
		//String loggedInUserId = loggedInUser.getId();
		
		@SuppressWarnings("unchecked")
		List<JobApplication> jobapplications = (List<JobApplication>) jobApplicationDAO.getMyAppliedJobs(userId);//loggedInUserId
		log.debug("**********End of getMyAppliedJobs() method.");
		return new ResponseEntity<List<JobApplication>>(jobapplications, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/updateJobApplication/{id}")   // in URL we give/updateJobApplication/1
	public ResponseEntity<JobApplication> updateJobApplication(@PathVariable("id") int id, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of updateJobApplication() method.");
		
		{
				JobApplication jobApplication1=jobApplicationDAO.get(id);
				jobApplication1.setStatus(jobApplication.getStatus());//F-Forwarded
				
				jobApplicationDAO.update(jobApplication1);
				log.debug("**********End of updateJobApplication() method.");
			return new ResponseEntity<JobApplication>(jobApplication1, HttpStatus.OK);
		}
		
	}
	
	
	
	/*@GetMapping(value = "/getMyAppliedJobs")
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		Users loggedInUser = (Users) httpSession.getAttribute("loggedInUser");
		String loggedInUserId = loggedInUser.getId();
		
		@SuppressWarnings("unchecked")
		List<Job> jobs = (List<Job>) jobDAO.getMyAppliedJobs(loggedInUserId);
		log.debug("**********End of getMyAppliedJobs() method.");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}*/
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value = "/getJobApplication/{userId}/{jobId}")
	public ResponseEntity<JobApplication> getJobApplication(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of getJobApplication() method.");
		JobApplication jobApplication1 = jobApplicationDAO.get(userId, jobId);
		if(jobApplication1 == null) {
			jobApplication1 = new JobApplication();
			jobApplication1.setErrorMessage("No jobApplication exist with the given userId and jobId");
			log.error("No jobApplication exist with the given UserId and jobId");
			return new ResponseEntity<JobApplication>(jobApplication1, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getJobApplication() method.");
		return new ResponseEntity<JobApplication>(jobApplication1, HttpStatus.OK);
	}

	
	/*@PutMapping(value = "/callForInterview/{userId}/{jobId}")
	public ResponseEntity<JobApplication> callForInterview(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of callForInterview() method.");
		jobApplication = jobApplicationDAO.get(userId, jobId);
		jobApplication.setStatus("C");
		if (jobApplicationDAO.updateJobApplication(jobApplication) == false) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("Not able to change job application status to 'call for interview'...");
			log.error("Not able to change job application status to 'call for interview'...");
		}
		log.debug("**********End of callForInterview() method.");
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}*/
	
	
	@PutMapping(value = "/callForInterview/{id}")   // in URL we give/callForInterview/1
	public ResponseEntity<JobApplication> callForInterview(@PathVariable("id") int id, @RequestBody JobApplication jobApplication)
	{
		log.debug("**********Starting of callForInterview() method.");
		
		{
				JobApplication jobApplication2=jobApplicationDAO.get(id);
				//jobApplication2.setStatus(jobApplication.getStatus());//C-callForInterview
				jobApplication2.setStatus("C");	// C= Call for Interview, R = Reject Job Application, A = Applied
				jobApplicationDAO.update(jobApplication2);
				log.debug("**********End of callForInterview() method.");
			return new ResponseEntity<JobApplication>(jobApplication2, HttpStatus.OK);
		}
	}
	
	
	/*@PutMapping(value = "/rejectJobApplication/{userId}/{jobId}")
	public ResponseEntity<JobApplication> rejectJobApplication(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of rejectJobApplication() method.");
		jobApplication = jobApplicationDAO.get(userId, jobId);
		jobApplication.setStatus("R");
		if (jobApplicationDAO.updateJobApplication(jobApplication) == false) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("Not able to reject the application...");
			log.error("Not able to reject the application...");
		}
		log.debug("**********End of rejectJobApplication() method.");
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}*/
	
	
	@PutMapping(value = "/rejectJobApplication/{id}")   // in URL we give/rejectJobApplication/1
	public ResponseEntity<JobApplication> rejectJobApplication(@PathVariable("id") int id, @RequestBody JobApplication jobApplication) 
	{
		log.debug("**********Starting of rejectJobApplication() method.");
		
		{
				JobApplication jobApplication3=jobApplicationDAO.get(id);
				//jobApplication3.setStatus(jobApplication.getStatus());//R-Rejected
				jobApplication3.setStatus("R");	// C= Call for Interview, R = Reject Job Application, A = Applied
				jobApplicationDAO.update(jobApplication3);
				log.debug("**********End of rejectJobApplication() method.");
			return new ResponseEntity<JobApplication>(jobApplication3, HttpStatus.OK);
		}
		
	}
}
