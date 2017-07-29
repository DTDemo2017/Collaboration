package com.niit.collaborationplatform.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationplatform.dao.FriendDAO;
import com.niit.collaborationplatform.dao.UserDAO;
import com.niit.collaborationplatform.model.Friend;
import com.niit.collaborationplatform.model.Users;

@RestController
public class UserController {
	
Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	@Autowired
	Users users;
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<Users>> listUsers() {
		log.debug("**********Starting of listUsers() method.");
		List<Users> users = userDAO.list();
		if(users.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listUsers() method.");
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
	
	/*@PostMapping(value = "/user/addFriend/{friendId}")			
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId) {
		
		log.debug("**********Starting of sendFriendRequest() method");
		String userId = (String) session.getAttribute("loggedInUserID");
		
		Users loggedInUser = (Users) session.getAttribute("loggedInUser");
		String userName = loggedInUser.getName();
		
		friend.setUserId(userId);
		friend.setUserName(userName);
		friend.setFriendId(friendId);
		friend.setIsOnline("Y");
		friend.setStatus("N");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		
		
		friendDAO.save(friend);
		
		log.debug("**********End of sendFriendRequest() method");		
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}*/
	
	@PostMapping(value = "/user/sendFriendRequest")//I made this method
	public ResponseEntity<Friend> sendFriendRequest(@RequestBody Users users, HttpSession httpSession) {
	              log.debug("**********Starting of sendFriendRequest() method.");
	      {
	              Users loggedInUser = (Users)httpSession.getAttribute("loggedInUser");
	              friend.setUserId(loggedInUser.getId());
	              friend.setUserName(loggedInUser.getName());
	              friend.setIsOnline("Y");
	              friend.setStatus("N"); // N = New, A = Accepted, R = Rejected, U = Unfriended 
	              friend.setFriendId(users.getId());
	              friend.setFriendName(users.getName());

	              friendDAO.save(friend);
	         
	              log.debug("**********End of sendFriendRequest() method.");
	              return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
	}

	



@PostMapping(value = "/user/")
public ResponseEntity<Users> saveUser(@RequestBody Users users, HttpSession session) {
	log.debug("**********Starting of saveUser() method.");
	
	{
		
		//users.setId(2);//
		//users.setName("Arushi ");//
		//users.setPassword("arushi ");//
		//users.setEmail("arushisinha@hotmail.com ");//
		users.setRole("Student ");
		users.setStatus("N ");
		users.setIsOnline("Y ");
		
		
		userDAO.save(users);
		log.debug("**********End of saveUser() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
		
	}
}
	
	@PutMapping(value = "/updateUser/{id}")   // in URL we give/updateUser/1
	public ResponseEntity<Users> updateUser(@PathVariable("id") String id, @RequestBody Users users) {
		log.debug("**********Starting of updateUser() method.");
		
		{
				Users users1=userDAO.get(id);
				users1.setStatus(users.getStatus());
				userDAO.update(users1);
				log.debug("**********End of updateUser() method.");
			return new ResponseEntity<Users>(users1, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("id") String id) {
		log.debug("**********Starting of deleteUser() method.");
		Users users = userDAO.get(id);
		if(users == null) {
			users = new Users();
			users.setErrorMessage("No user exist with id : " + id);
			log.error("No user exist with id : " + id);
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		userDAO.delete(users);
		log.debug("**********End of deleteUser() method.");
		return new ResponseEntity<Users>(HttpStatus.OK);		
	}
	
	
	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<Users> getUser(@PathVariable("id") String id) {
		log.debug("**********Starting of getUser() method.");
		Users users = userDAO.get(id);
		if(users == null) {
			users = new Users();
			users.setErrorMessage("No user exist with id : " + id);
			log.error("No user exist with id : " + id);
			return new ResponseEntity<Users>(users, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getUser() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<Users> myProfile() {
		log.debug("->->calling method myProfile");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		Users user = userDAO.get(loggedInUserID);
		if (user == null) {
			log.debug("->->->-> User does not exist wiht id" + loggedInUserID);
			user = new Users(); // It does not mean that we are inserting new row
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<Users>(user, HttpStatus.NOT_FOUND);
		}
		log.debug("->->->-> User exist with id" + loggedInUserID);
		log.debug(user.getName());
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/user/login")
	public ResponseEntity<Users> login(@RequestBody Users users, HttpSession session) {
		log.debug("**********Starting of login() method.");
		users = userDAO.authenticate(users.getId(), users.getPassword());
		if(users == null) {
			users = new Users();	//we need to create new users object to set errorMsg and errorCode...
			users.setErrorCode("404");
			users.setErrorMessage("Invalid userId or password...");
			log.error("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			session.setAttribute("loggedInUserID", users.getId());
			session.setAttribute("LoggedInStatus", users.getIsOnline());
			
			//friendDAO.setOnline(users.getId());
			userDAO.setOnline(users.getId());
		}
		log.debug("**********End of login() method.");
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/user/logout")
	public ResponseEntity<Users> logout(Users users, HttpSession session) {
		log.debug("**********Starting of logout() method.");
		
	String userId = (String) session.getAttribute("loggedInUserID");
		
		log.debug("**********"+userId+"**********");
		
		//friendDAO.setOffline(userId);
		userDAO.setOffline(userId);
		users.setIsOnline("N");
		
		session.invalidate();
		log.debug("**********End of logout() method.");
		return new ResponseEntity<Users> (HttpStatus.OK);
	}
	

}
