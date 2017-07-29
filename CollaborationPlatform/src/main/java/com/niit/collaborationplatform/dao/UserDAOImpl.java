package com.niit.collaborationplatform.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationplatform.model.Users;


@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
Logger log = Logger.getLogger(UserDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	public UserDAOImpl(){}

	
	
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
	public boolean save(Users users) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			
			users.setStatus("N");	
			// N = New, R = Rejected, A = Approved 
			
			session.save(users);
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
	public boolean update(Users users) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(users);
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
	public boolean saveOrUpdate(Users users) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(users);
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
	public boolean delete(Users users) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(users);
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
	public Users get(String id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Users where id = ?");
		
		
		query.setString(0, id);
		log.debug("**********End of get() method.");
		return (Users) query.uniqueResult();
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Users> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Users");
		List<Users> usersList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return usersList;
	}


	public void setOnline(String id) {
		log.debug("**********Starting of setOnline() method.");
		String s = "update Users set isOnline = 'Y' where id = ? ";
		Session session=getSession();
		Query query = session.createQuery(s);
		query.setString(0, id);
		query.executeUpdate();
		log.debug("**********End of setOnline() method.");
		
	}


	public void setOffline(String id) {
		log.debug("**********Starting of setOffline() method.");
		String s = "update Users set isOnline = 'N' where id = ? ";
		Session session=getSession();
		Query query = session.createQuery(s);
		query.setString(0, id);
		query.executeUpdate();
		log.debug("**********End of setOffline() method.");
		
	}


	public Users authenticate(String id, String password) {
		String s = "from Users where id = ?  and  password = ?";
		Session session=getSession();
		
		Query query = session.createQuery(s);
		query.setString(0, id);
		query.setString(1, password);
		
		List<Users> list = (List<Users>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
