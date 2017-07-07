package com.niit.collaborationplatform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationplatform.dao.BlogDAO;
import com.niit.collaborationplatform.model.Blog;

@RestController
public class BlogController {
	
	Logger log = Logger.getLogger(BlogController.class);
	
	@Autowired
	BlogDAO blogDAO;
	
	/**
	 * 	http://localhost:9500/CollaborationPlatform/blogs			//working
	 * @return
	 */
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlogs() {
		log.debug("**********Starting of listBlogs() method.");
		List<Blog> blog = blogDAO.list();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listBlogs() method.");
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:9500/CollaborationPlatform/blog/			//working
	 * @param blog
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/blog/")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog, HttpSession session) {
		log.debug("**********Starting of saveBlog() method.");
		
		{
			
			blog.setUserId("U1");
			blog.setCountLike(3);
			blog.setStatus("N");
			blog.setPostDate(new Date());
			blog.setUserName("Ashwini");
			blogDAO.save(blog);
			log.debug("**********End of saveBlog() method.");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			
		}
		
	}

	/**
	 * 	http://localhost:9500/CollaborationPlatform/blog/{id}			//working
	 * @param id
	 * @param blog
	 * @return
	 */
	@PutMapping(value = "/updateBlog/{id}")   // in URL we give/updateBlog/1
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		log.debug("**********Starting of updateBlog() method.");
		//if(blogDAO.get(id) == null) 
		{
			//blog = new Blog();
			//blog.setErrorMessage("No blog exist with id : " +blog.getId());
			//log.error("No blog exist with id : " +blog.getId());
			}
		//return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			//else
		{
				Blog blog1=blogDAO.get(id);
				blog1.setStatus(blog.getStatus());
				//blog1.setContent(blog.getContent());
				blogDAO.update(blog1);
				log.debug("**********End of updateBlog() method.");
			return new ResponseEntity<Blog>(blog1, HttpStatus.OK);
		}
		//blogDAO.update(blog);
		//log.debug("**********End of updateBlog() method.");
		//return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	/**
	 * 	http://localhost:9500/CollaborationPlatform/blog/{id}			//working
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/deleteBlog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteBlog() method.");
		Blog blog = blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			log.error("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDAO.delete(blog);
		log.debug("**********End of deleteBlog() method.");
		return new ResponseEntity<Blog>(HttpStatus.OK);		
	}
	
	/**
	 * 	http://localhost:9500/CollaborationPlatform/blog/{id}			//working
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getBlog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id) {
		log.debug("**********Starting of getBlog() method.");
		Blog blog = blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			log.error("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
		
	
	/**
	 * http://localhost:9500/CollaborationPlatform/approveBlog/{id}
	 * @param id
	 * @param blog
	 * @return
	 */
	@PutMapping(value = "/approveBlog/{id}")				
	public ResponseEntity<Blog> approveBlog( @PathVariable("id") int id,@RequestBody Blog blog) {
		log.debug("**********Starting of approveBlog() method.");
		Blog blog1=blogDAO.get(id);
		blog1.setStatus(blog.getStatus());
		//blog.setStatus("A");	// A = Accept, R = Reject, N = New
		blogDAO.update(blog1);
		
		log.debug("**********End of approveBlog() method.");
		return new ResponseEntity<Blog> (blog1, HttpStatus.OK);
	}
	/**
	 * http://localhost:9500/CollaborationPlatform/rejectBlog/{id}
	 * @param id
	 * @param blog
	 * @return
	 */
	@PutMapping(value = "/rejectBlog/{id}")				
	public ResponseEntity<Blog> rejectBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		log.debug("**********Starting of rejectBlog() method.");
		Blog blog1=blogDAO.get(id);
		blog1.setStatus(blog.getStatus());
		//blog.setStatus("R");	// A = Accept, R = Reject, N = New
		blogDAO.update(blog1);
		
		log.debug("**********End of rejectBlog() method.");
		return new ResponseEntity<Blog> (blog1, HttpStatus.OK);
	}
	
	/**
	 * http://localhost:9500/CollaborationPlatform/likeBlog/{id}
	 * @param id
	 * @param blog
	 * @return
	 */
	@PutMapping(value = "/likeBlog/{id}")
	public ResponseEntity<Blog> likeBlog(@PathVariable("id") int id, @RequestBody Blog blog){
		log.debug("**********Starting of likeBlog() method.");
		Blog blog1=blogDAO.get(id);
		
		blog1.setCountLike(blog.getCountLike()+1);
		
		
		
		  blogDAO.update(blog1);
		
		log.debug("**********End of likeBlog() method.");
		return new ResponseEntity<Blog>(blog1, HttpStatus.OK);
	}
}

