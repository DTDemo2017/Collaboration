package com.niit.collaborationplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationplatform.model.Blog;


//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
@Repository
public interface BlogDAO {
	
	/**
	 * Declare all CRUD Operations...
	 * 
	 */	   
	 	
	

public boolean save(Blog blog);
	
	
public boolean update(Blog blog);



public boolean approve(Blog blog);



public boolean reject(Blog blog);


public boolean like(Blog blog);
	
	
public boolean saveOrUpdate(Blog blog);
	
	
public boolean delete(Blog blog);
	
	
public Blog get(int id);
	
	
public List<Blog> list();
	
	
//public Blog getLike(int id);



}
