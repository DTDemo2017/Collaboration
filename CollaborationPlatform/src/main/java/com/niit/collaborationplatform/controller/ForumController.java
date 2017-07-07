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

import com.niit.collaborationplatform.dao.ForumDAO;
import com.niit.collaborationplatform.model.Blog;
import com.niit.collaborationplatform.model.Forum;

@RestController
public class ForumController {
Logger log = Logger.getLogger(ForumController.class);
	
	@Autowired
	ForumDAO forumDAO;
	
	@GetMapping(value = "/forums")
	public ResponseEntity<List<Forum>> listForums() {
		log.debug("**********Starting of listForums() method.");
		List<Forum> forum = forumDAO.list();
		if(forum.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listForums() method.");
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}



@PostMapping(value = "/forum/")
public ResponseEntity<Forum> saveForum(@RequestBody Forum forum, HttpSession session) {
	log.debug("**********Starting of saveForum() method.");
	
	{
		
		//forum.setId(1);
		
		//forum.setDescription("Assemble Here ");
		  forum.setPostDate(new Date());
		//forum.setUserId("U1 ");
		  forum.setStatus("N");
		  forum.setUserName("Anita");
		  
		
		
		
		forumDAO.save(forum);
		log.debug("**********End of saveForum() method.");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		
	}
}
	
	@PutMapping(value = "/updateForum/{id}")   // in URL we give/updateBlog/1
	public ResponseEntity<Forum> updateForum(@PathVariable("id") int id, @RequestBody Forum forum) {
		log.debug("**********Starting of updateForum() method.");
		
		{
				Forum forum1=forumDAO.get(id);
				//forum1.setDescription(forum.getDescription());
				forum1.setStatus(forum.getStatus());
				forumDAO.update(forum1);
				log.debug("**********End of updateForum() method.");
			return new ResponseEntity<Forum>(forum1, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping(value = "/deleteForum/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteForum() method.");
		Forum forum = forumDAO.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDAO.delete(forum);
		log.debug("**********End of deleteForum() method.");
		return new ResponseEntity<Forum>(HttpStatus.OK);		
	}
	
	
	@GetMapping(value = "/getForum/{id}")
	public ResponseEntity<Forum> getForum(@PathVariable("id") int id) {
		log.debug("**********Starting of getForum() method.");
		Forum forum = forumDAO.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getForum() method.");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/approveForum/{id}")				
	public ResponseEntity<Forum> approveForum( @PathVariable("id") int id,@RequestBody Forum forum) {
		log.debug("**********Starting of approveForum() method.");
		Forum forum1=forumDAO.get(id);
		forum1.setStatus(forum.getStatus());
		//blog.setStatus("A");	// A = Accept, R = Reject, N = New
		forumDAO.update(forum1);
		
		log.debug("**********End of approveForum() method.");
		return new ResponseEntity<Forum> (forum1, HttpStatus.OK);
	}

}
