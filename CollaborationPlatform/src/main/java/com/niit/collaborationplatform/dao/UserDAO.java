package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.Users;

@Repository
public interface UserDAO {
	
	public boolean save(Users users);
	
	
	public boolean update(Users users);
		
		
	public boolean saveOrUpdate(Users users);
		
		
	public boolean delete(Users users);
		
		
	public Users get(String id);
		
		
	public List<Users> list();
	
	
	public void setOnline (String id);
	
	
	public void setOffline (String id);
	
	
	public Users authenticate(String id, String password);

}
