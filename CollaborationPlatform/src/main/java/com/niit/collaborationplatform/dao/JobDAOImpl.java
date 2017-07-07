package com.niit.collaborationplatform.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationplatform.model.Job;
import com.niit.collaborationplatform.model.JobApplication;


@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {
	
	
Logger log = Logger.getLogger(JobDAOImpl.class);

   @Autowired
   Job job;
   
   @Autowired JobApplication jobApplication;
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public JobDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	public JobDAOImpl(){}

	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	protected Session getSession(){
		return sessionFactory.openSession();
	}
	
	
	
	@Transactional
	public boolean save(Job job) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			job.setJobDate(new Date(System.currentTimeMillis()));    
			//save current time as postDate
			job.setStatus("V");	
			//V-Vacant	F-Filled	P-Pending 
			
			session.save(job);
			session.flush();
			session.close();
			log.debug("**********End of save() method.");

			
			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}
	
	
	@Transactional
	public boolean update(Job job) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(job);
			session.flush();
			session.close();
			
			
			log.debug("**********End of update() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}
	
	
	@Transactional
	public boolean saveOrUpdate(Job job) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(job);
			session.flush();
			session.close();
			log.debug("**********End of saveOrUpdate() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());
				e.printStackTrace();
				return false;
			}
	}
	
	
	@Transactional
	public boolean delete(Job job) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(job);
			session.flush();
			session.close();
			log.debug("**********End of delete() method.");

			return true;
		}catch (Exception e){
			log.error("Error occured : " + e.getMessage());

			e.printStackTrace();
			return false;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Job get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Job where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Job) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Job");
		List<Job> jobList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return jobList;
	}


	public List<Job> listVacantJobs() {
		log.debug("**********Starting of listVacantJobs() method.");
		String hql = "from Job where status = 'V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of listVacantJobs() method.");
		return query.list();
	}


	
	//In JobApplicationDAOImpl
	/*public List<Job> getMyAppliedJobs(String userId) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		String hql = "from Job where id in (select jobId from JobApplication where userId = '" + userId + "')";
		log.debug("******hql query : "+hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of getMyAppliedJobs() method.");
		return query.list();
	}*/


	public List<JobApplication> listJobApplications() {
		log.debug("**********Starting of listJobApplications() method.");
		String hql = "from JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of listJobApplications() method.");
		return query.list();
	}


	/*public boolean applyForJob(JobApplication jobApplication) {
		try {
			log.debug("**********Starting of applyForJob() method.");
                                                          
			
			Session session = getSession();
            jobApplication.setStatus("A");//A=Applied, R=Rejected, C=Call for Interview
			
            session.save(jobApplication);
			session.flush();
			session.close();
			log.debug("**********End of applyForJob() method.");
			return true;
		}
                                                         catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}*/


	/*public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			log.debug("**********Starting of updateJobApplication() method.");
                                                          Session session = getSession();
			session.update(jobApplication);
			session.flush();
			session.close();
			
			log.debug("**********End of updateJobApplication() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}*/


	/*public JobApplication get(String userId, String jobId) {
		log.debug("**********Starting of get() method.");
		String hql = "from JobApplication where userId = '" + userId + "' and jobId = '" + jobId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of get() method.");
		return (JobApplication) query.list();//not done in controller
	}*/
	
	
	

}
