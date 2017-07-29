package com.niit.collaborationplatform.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationplatform.model.JobApplication;

@Repository("jobApplicationDAO")
@Transactional
public class JobApplicationDAOImpl implements JobApplicationDAO {
	
	Logger log = Logger.getLogger(JobApplicationDAOImpl.class);
	
	 @Autowired JobApplication jobApplication;
		
		
		@Autowired
		private SessionFactory sessionFactory;
		
		
		public JobApplicationDAOImpl(SessionFactory sessionFactory){
			this.sessionFactory=sessionFactory;
		}
		
		
		public JobApplicationDAOImpl(){}

		
		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		
		
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		
		
		protected Session getSession(){
			return sessionFactory.openSession();
		}
		
		
		public boolean applyForJob(JobApplication jobApplication) {
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
		}
		
		

	 public boolean updateJobApplication(JobApplication jobApplication) {
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
	}


	public JobApplication get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from JobApplication where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (JobApplication) query.uniqueResult();
	}


	public void update(JobApplication jobApplication1) {
		try{
			Session session= getSession();
			session.update(jobApplication1);
			session.flush();
			session.close();
			
			
		   }
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}


	public List<JobApplication> getMyAppliedJobs(String userId) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		String hql = "from JobApplication  where userId = '" + userId + "' ";
		log.debug("******hql query : "+hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of getMyAppliedJobs() method.");
		return query.list();
	}
	
	
	/*public JobApplication get(String userId, String jobId) {
		log.debug("**********Starting of get() method.");
		String hql = "from JobApplication where userId = '" + userId + "' and jobId = '" + jobId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of get() method.");
		return (JobApplication) query.list();// done in controller, needed for callForInterview & Reject
	}*/
	
	
	public JobApplication get(String userId, String jobId) {
		log.debug("**********Starting of get() method.");
                                       Session session = getSession();
                                       Query query = session.createQuery("from JobApplication where userId = ? and jobId=?");
                                       query.setString(0, userId);
                                       query.setString(1, jobId);
		
		log.debug("**********End of get() method.");
		return (JobApplication) query.uniqueResult();// done in controller, needed for callForInterview & Reject
	}	



	public boolean callForInterview(JobApplication jobApplication) {
		try{
			log.debug("**********Starting of callForInterview() method.");

			Session session = getSession();
				
			session.update(jobApplication);
			session.flush();
			session.close();
			
			
			log.debug("**********End of callForInterview() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}


	

	public boolean reject(JobApplication jobapplication) {
		try {
			log.debug("**********Starting of rejectJobApplication() method.");
            Session session = getSession();
            
			session.update(jobApplication);
			session.flush();
			session.close();
			
			log.debug("**********End of rejectJobApplication() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}


	

}
