package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.Friend;

@Repository
public interface FriendDAO {
	
	public boolean save(Friend friend);
	
	
	public boolean update(Friend friend);
		
		
	public boolean saveOrUpdate(Friend friend);
		
		
	public boolean delete(Friend friend);
		
		
	public Friend get(int id);
		
		
	public List<Friend> list();


	public List<Friend> getMyFriends(String userId);


	public List<Friend> getNewFriendRequests(String userId);
	
	

}
