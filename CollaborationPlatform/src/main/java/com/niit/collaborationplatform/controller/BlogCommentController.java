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

import com.niit.collaborationplatform.dao.BlogCommentDAO;
import com.niit.collaborationplatform.model.BlogComment;

@RestController
public class BlogCommentController {
Logger log = Logger.getLogger(BlogCommentController.class);
	
	@Autowired
	BlogCommentDAO blogcommentDAO;
	
	
	
	
	@GetMapping(value = "/blogcomments")
	public ResponseEntity<List<BlogComment>> listBlogComments() {
		log.debug("**********Starting of listBlogComments() method.");
		List<BlogComment> blogcomment = blogcommentDAO.list();
		if(blogcomment.isEmpty()) {
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listBlogComments() method.");
		return new ResponseEntity<List<BlogComment>>(blogcomment, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping(value = "/blogcomment/")
	public ResponseEntity<BlogComment> saveBlogComment(@RequestBody BlogComment blogcomment, HttpSession session) {
		log.debug("**********Starting of saveBlogComment() method.");
		
		{
			
			//blogcomment.setId(9);
			blogcomment.setBlogId(1);
			//blogcomment.setComments("I like learning from the maven blog!");
			blogcomment.setCommentDate(new Date());
			blogcomment.setUserId("U7");
			//blogcomment.setUserName("Aashi");
			blogcomment.setBlogName("Core Java");
			blogcommentDAO.save(blogcomment);
			log.debug("**********End of saveBlogComment() method.");
			return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.OK);
			
		}
		
	}

	
	
	@PutMapping(value = "/updateBlogComment/{id}")   // in URL we give/updateBlogComment/1
	public ResponseEntity<BlogComment> updateBlogComment(@PathVariable("id") int id, @RequestBody BlogComment blogcomment) {
		log.debug("**********Starting of updateBlogComment() method.");
		
		{
				BlogComment blogcomment1=blogcommentDAO.get(id);
				blogcomment1.setComments(blogcomment.getComments());
				blogcommentDAO.update(blogcomment1);
				log.debug("**********End of updateBlogComment() method.");
			return new ResponseEntity<BlogComment>(blogcomment1, HttpStatus.OK);
		}
		
	}
	
	
	
	@DeleteMapping(value = "/deleteBlogComment/{id}")
	public ResponseEntity<BlogComment> deleteBlogComment(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteBlogComment() method.");
		BlogComment blogcomment = blogcommentDAO.get(id);
		if(blogcomment == null) {
			blogcomment = new BlogComment();
			blogcomment.setErrorMessage("No blogcomment exist with id : " + id);
			log.error("No blogcomment exist with id : " + id);
			return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.NOT_FOUND);
		}
		blogcommentDAO.delete(blogcomment);
		log.debug("**********End of deleteBlogComment() method.");
		return new ResponseEntity<BlogComment>(HttpStatus.OK);		
	}
	
	
	
	@GetMapping(value = "/getBlogComment/{id}")
	public ResponseEntity<BlogComment> getBlogComment(@PathVariable("id") int id) {
		log.debug("**********Starting of getBlogComment() method.");
		BlogComment blogcomment = blogcommentDAO.get(id);
		if(blogcomment == null) {
			blogcomment = new BlogComment();
			blogcomment.setErrorMessage("No blogcomment exist with id : " + id);
			log.error("No blogcomment exist with id : " + id);
			return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getBlogComment() method.");
		return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.OK);
	}
	
	
	
}
