package com.example.demo.ui.controller;

 
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import com.example.demo.util.Utility;

import ch.qos.logback.classic.pattern.Util;

@RestController
@RequestMapping("userObjects")
public class UserObjectController {
	private List<UserRest> userList=new ArrayList<>();
	private Map<String,UserRest> userDetailsMap;
	

	//http://localhost:8080/userObjects?limit=80&sort=test&userId=0
	@GetMapping
	public  UserRest getUsers(@RequestParam(value="userId", defaultValue = "1") String userId,
			@RequestParam(value="limit") String limit,@RequestParam(value="sort", required = false, defaultValue = "asc") String sort) 
	{
		if (sort== null) sort="Desc"; 
		if (sort.equals("asc")) sort="Assending"; 
		
		if (null!= userId) {
			return userDetailsMap.get(userId);
		}
		
		return userDetailsMap.get(userId);
	}
	
	
	// http://localhost:8080/userObjects/1
	
	@GetMapping(path="/{userId}", 
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public  ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		
		return new ResponseEntity<UserRest>(userDetailsMap.get(userId),HttpStatus.OK);
	}	
	
	
	// http://localhost:8080/userObjects
	
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE}, consumes = {
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) 
	{
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetailsRequestModel.getEmail());
		returnValue.setFirstName(userDetailsRequestModel.getFirstName());
		returnValue.setLastName(userDetailsRequestModel.getLastName());
		returnValue.setPhoneNumber(userDetailsRequestModel.getPhoneNumber());
		
		if(null==userDetailsMap)userDetailsMap = new HashMap();
		
		String userID=Utility.getRandomUserId();
		returnValue.setUserId(userID);	
		userDetailsMap.put(userID, returnValue);
		
		
	 return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
	}



	
	
	@PutMapping(produces = {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> updateUser(@RequestParam(value="userId") String userId, @Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) 
	{
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetailsRequestModel.getEmail());
		returnValue.setFirstName(userDetailsRequestModel.getFirstName());
		returnValue.setLastName(userDetailsRequestModel.getLastName());
		returnValue.setPhoneNumber(userDetailsRequestModel.getPhoneNumber());
		returnValue.setUserId( userDetailsRequestModel.getUserId());
	
		
		userDetailsMap.remove(userId);
		
		 
		userDetailsMap.put(userId, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
	}
	
	@DeleteMapping
	public String deleteUser(@RequestParam(value="userId", defaultValue = "1") Integer userId) 
	{
		 
		
		return "Deleted user details for "  + userId;
	}
	
	
	public UserObjectController(List<UserRest> userList) {
		super();
		this.userList = userList;
		
		UserRest returnValue = new UserRest();
	 
		returnValue.setFirstName("Pragati");
		returnValue.setLastName("Singh");
		returnValue.setPhoneNumber("9999937921");
		returnValue.setEmail("myemailid@google.com");
		returnValue.setUserId(Utility.getRandomUserId());
		userList.add(returnValue);
/////////////////////////////////////////////////////////
		UserRest returnValue1 = new UserRest();
		returnValue1.setEmail("yourmail@google.com");
		returnValue1.setFirstName("Your");
		returnValue1.setLastName("Name");
		returnValue1.setPhoneNumber("99999xxxxx");
		 
		returnValue1.setUserId(Utility.getRandomUserId());
		
		userList.add(returnValue1);
	}
}
