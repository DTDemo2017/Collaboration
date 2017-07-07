package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.ForumComment;

@Repository
public interface ForumCommentDAO {
	
    public boolean save(ForumComment forumcomment);
	
	
	public boolean update(ForumComment forumcomment);
		
		
	public boolean saveOrUpdate(ForumComment forumcomment);
		
		
	public boolean delete(ForumComment forumcomment);
		
		
	public ForumComment get(int id);
		
	
	public List<ForumComment> getComments(int forumId);


	public List<ForumComment> list();

}
