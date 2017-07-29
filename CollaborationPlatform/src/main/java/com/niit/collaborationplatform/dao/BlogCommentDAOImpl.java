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


@Repository("blogcommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	Logger log = Logger.getLogger(BlogCommentDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogCommentDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public BlogCommentDAOImpl(){}

	
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
	public boolean save(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			blogcomment.setCommentDate(new Date(System.currentTimeMillis()));    
			//save current time as commentDate
			
			
			session.save(blogcomment);
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
	public boolean update(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(blogcomment);
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
	public boolean saveOrUpdate(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(blogcomment);
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
	public boolean delete(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(blogcomment);
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
	public BlogComment get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from BlogComment where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (BlogComment) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<BlogComment> getComments(int blogId) {
		Session session = getSession();
		String h = "from BlogComment where blogId=" + "'" + blogId + "'";
		Query q = session.createQuery(h);
		return q.list();
	}

	public List<BlogComment> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from BlogComment");
		List<BlogComment> blogcommentList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return blogcommentList;
	}

	public List<BlogComment> list(int blogId) {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from BlogComment where blogid=?");
		List<BlogComment> blogcommentList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return blogcommentList;
	}

	

}
