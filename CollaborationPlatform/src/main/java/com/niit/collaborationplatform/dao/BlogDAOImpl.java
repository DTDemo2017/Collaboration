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

import com.niit.collaborationplatform.model.Blog;



@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {
	
	
	Logger log = Logger.getLogger(BlogDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public BlogDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	public BlogDAOImpl(){}

	
	
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
	public boolean save(Blog blog) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			blog.setPostDate(new Date(System.currentTimeMillis()));    
			//save current time as postDate
			blog.setStatus("N");	
			// N = New, R = Rejected, A = Approved 
			
			session.save(blog);
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
	public boolean update(Blog blog1) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(blog1);
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
	public boolean saveOrUpdate(Blog blog) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(blog);
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
	public boolean delete(Blog blog) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(blog);
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

	/*public Blog get(int id) {
		 Session session = getSession();
			
			return (Blog) session.get(Blog.class, id);
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Blog get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Blog) query.uniqueResult();
		

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Blog> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog");
		List<Blog> blogList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return blogList;
	}

	@Transactional
	public boolean approve(Blog blog) {
		try{
			log.debug("**********Starting of approve() method.");

			Session session = getSession();
			session.update(blog);
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

	@Transactional
	public boolean reject(Blog blog) {
		try{
			log.debug("**********Starting of reject() method.");

			Session session = getSession();
			session.update(blog);
			session.flush();
			session.close();
			
			
			log.debug("**********End of reject() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}

	@Transactional
	public boolean like(Blog blog) {
		try{
			log.debug("**********Starting of like() method.");

			Session session = getSession();
			session.update(blog);
			session.flush();
			session.close();
			
			
			log.debug("**********End of like() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}


	
	


	

}
