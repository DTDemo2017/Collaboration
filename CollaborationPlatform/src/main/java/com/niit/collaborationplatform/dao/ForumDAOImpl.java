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


import com.niit.collaborationplatform.model.Forum;
import com.niit.collaborationplatform.model.ForumComment;


@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {
	
	Logger log = Logger.getLogger(ForumDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public ForumDAOImpl(){}

	
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
	public boolean save(Forum forum) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			forum.setPostDate(new Date(System.currentTimeMillis()));    
			//save current time as postDate
			forum.setStatus("N");	
			// N = New, R = Rejected, A = Approved 
			
			session.save(forum);
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
	public boolean update(Forum forum) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(forum);
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
	public boolean saveOrUpdate(Forum forum) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(forum);
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
	public boolean delete(Forum forum) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(forum);
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
	public Forum get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Forum where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Forum) query.uniqueResult();
		
	}
    
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Forum> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Forum");
		List<Forum> forumList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return forumList;
	}
	
	@Transactional
	public ForumComment getComments(int id) {
		String hql = "from ForumComment where id = ?";
		Session session = getSession();
		Query query = session.createQuery(hql);
		
		query.setInteger(0, id);
		List<ForumComment> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	
	@Transactional
	public List<ForumComment> listComments(int id) {
		
		Session session = getSession();
		String s = " from ForumComment where id = ?" ;
		Query query = session.createQuery(s);
		query.setInteger(0, id);
		return query.list();
	}
    
	
	@Transactional
	public boolean approve(Forum forum) {
		try{
			log.debug("**********Starting of approve() method.");

			Session session = getSession();
			session.update(forum);
			session.flush();
			session.close();
			
			
			log.debug("**********End of approve() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}

}
