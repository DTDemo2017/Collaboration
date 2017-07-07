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

import com.niit.collaborationplatform.dao.FriendDAO;
import com.niit.collaborationplatform.model.Friend;

@RestController
public class FriendController {
Logger log = Logger.getLogger(FriendController.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@GetMapping(value = "/friends")
	public ResponseEntity<List<Friend>> listFriends() {
		log.debug("**********Starting of listFriends() method.");
		List<Friend> friend = friendDAO.list();
		if(friend.isEmpty()) {
			return new ResponseEntity<List<Friend>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listFriends() method.");
		return new ResponseEntity<List<Friend>>(friend, HttpStatus.OK);
	}



@PostMapping(value = "/friend/")
public ResponseEntity<Friend> saveFriend(@RequestBody Friend friend, HttpSession session) {
	log.debug("**********Starting of saveFriend() method.");
	
	{
		
		//friend.setId(1);
		//friend.setUserId("U1 ");
		//friend.setFriendId("U2");
		friend.setFriendDate(new Date());
		  friend.setStatus("N");
		  friend.setIsOnline("Y ");
		 // friend.setUserName("AMAY ");
		  //friend.setFriendName("AJAY ");
		
		
		
		friendDAO.save(friend);
		log.debug("**********End of saveFriend() method.");
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
}
	
	@PutMapping(value = "/updateFriend/{id}")   // in URL we give/updateBlog/1
	public ResponseEntity<Friend> updateFriend(@PathVariable("id") int id, @RequestBody Friend friend) {
		log.debug("**********Starting of updateFriend() method.");
		
		{
				Friend friend1=friendDAO.get(id);
				friend1.setStatus(friend.getStatus());
				friendDAO.update(friend1);
				log.debug("**********End of updateFriend() method.");
			return new ResponseEntity<Friend>(friend1, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping(value = "/deleteFriend/{id}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteFriend() method.");
		Friend friend = friendDAO.get(id);
		if(friend == null) {
			friend = new Friend();
			friend.setErrorMessage("No friend exist with id : " + id);
			log.error("No friend exist with id : " + id);
			return new ResponseEntity<Friend>(friend, HttpStatus.NOT_FOUND);
		}
		friendDAO.delete(friend);
		log.debug("**********End of deleteFriend() method.");
		return new ResponseEntity<Friend>(HttpStatus.OK);		
	}
	
	
	@GetMapping(value = "/getFriend/{id}")
	public ResponseEntity<Friend> getFriend(@PathVariable("id") int id) {
		log.debug("**********Starting of getFriend() method.");
		Friend friend = friendDAO.get(id);
		if(friend == null) {
			friend = new Friend();
			friend.setErrorMessage("No friend exist with id : " + id);
			log.error("No friend exist with id : " + id);
			return new ResponseEntity<Friend>(friend, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getFriend() method.");
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	

}
