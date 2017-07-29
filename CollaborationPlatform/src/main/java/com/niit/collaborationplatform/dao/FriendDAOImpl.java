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

import com.niit.collaborationplatform.model.Friend;


@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {
	
Logger log = Logger.getLogger(FriendDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public FriendDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	
	public FriendDAOImpl(){}

	
	
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
	public boolean save(Friend friend) {
		try{
			log.debug("**********Starting of save() method.");

			Session session = getSession();
			friend.setFriendDate(new Date(System.currentTimeMillis()));
			friend.setStatus("N");	
			// N = New, R = Rejected, A = Approved 
			
			session.save(friend);
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
	public boolean update(Friend friend) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(friend);
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
	public boolean saveOrUpdate(Friend friend) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(friend);
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
	public boolean delete(Friend friend) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(friend);
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
	public Friend get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Friend where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Friend) query.uniqueResult();
	}
    
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Friend> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Friend");
		List<Friend> friendList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return friendList;
	}

	@Transactional
	public List<Friend> getMyFriends(String userId) {
		log.debug("**********Starting of getMyFriends() method.");
		String hql = "from Friend where (userId = '" + userId + "' and status = 'A') or (friendId = '" + userId + "' and status = 'A')";
		log.debug("**********hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		log.debug("**********End of getMyFriends() method.");
		return list;
	}

	@Transactional
	public List<Friend> getNewFriendRequests(String userId) {
		log.debug("**********Starting of getNewFriendRequests() method.");
		
		String hql = "from Friend where (friendId = '" + userId + "' and status = 'N') or (userId = '" + userId + "' and status = 'N')";
		log.debug("***********hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		log.debug("**********End of getNewFriendRequests() method.");
		return list;
	}



	
	
	

}
