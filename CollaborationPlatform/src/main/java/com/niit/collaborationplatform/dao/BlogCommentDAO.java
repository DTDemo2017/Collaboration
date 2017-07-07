package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.BlogComment;


@Repository
public interface BlogCommentDAO {
	
	public boolean save(BlogComment blogcomment);
	
	
	public boolean update(BlogComment blogcomment);
		
		
	public boolean saveOrUpdate(BlogComment blogcomment);
		
		
	public boolean delete(BlogComment blogcomment);
		
		
	public BlogComment get(int id);
		
	
	public List<BlogComment> getComments(int blogId);


	public List<BlogComment> list();
	
	
	public List<BlogComment> list(int blogId);
	
	
	


	
}
