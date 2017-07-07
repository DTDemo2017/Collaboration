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

import com.niit.collaborationplatform.model.BlogComment;
import com.niit.collaborationplatform.model.ForumComment;


@Repository("forumcommentDAO")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO {
	
Logger log = Logger.getLogger(ForumCommentDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumCommentDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public ForumCommentDAOImpl(){}

	
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
	public boolean save(ForumComment forumcomment) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			forumcomment.setCommentDate(new Date(System.currentTimeMillis()));    
			//save current time as commentDate
			
			
			session.save(forumcomment);
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
	public boolean update(ForumComment forumcomment) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(forumcomment);
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
	public boolean saveOrUpdate(ForumComment forumcomment) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(forumcomment);
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
	public boolean delete(ForumComment forumcomment) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(forumcomment);
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
	public ForumComment get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from ForumComment where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (ForumComment) query.uniqueResult();
	}
    
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ForumComment> getComments(int forumId) {
		Session session = getSession();
		String h = "from ForumComment where forumId=" + "'" + forumId + "'";
		Query q = session.createQuery(h);
		return q.list();
	}

	public List<ForumComment> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from ForumComment");
		List<ForumComment> forumcommentList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return forumcommentList;
	}

}
