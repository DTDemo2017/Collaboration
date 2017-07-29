package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.Blog;
import com.niit.collaborationplatform.model.Forum;
import com.niit.collaborationplatform.model.ForumComment;

@Repository
public interface ForumDAO {
	
	public boolean save(Forum forum);
	
	
	public boolean update(Forum forum);
	
	
	public boolean approve(Forum forum);
		
		
	public boolean saveOrUpdate(Forum forum);
		
		
	public boolean delete(Forum forum);
		
		
	public Forum get(int id);
		
		
	public List<Forum> list();
		
	
	
	public ForumComment getComments(int id);
	
		
	public List<ForumComment> listComments(int id);
}
