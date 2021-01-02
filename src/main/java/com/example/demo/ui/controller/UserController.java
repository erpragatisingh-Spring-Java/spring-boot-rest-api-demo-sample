package com.example.demo.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
	
	// Normal Requst with user iD
//	@GetMapping(path = "/{userId}")
//	public String getUser(@PathVariable String userId) 
//	{
//		
//		return "Get user details for User ID = "+ userId;
//	}
	
	
	// create optional or Required
	
	
	@GetMapping
	public String getUser(@RequestParam(value="page", defaultValue = "10") String pageid,
			@RequestParam(value="limit") String limit,@RequestParam(value="sort", required = false, defaultValue = "asc") String sort) 
	{
		if (sort== null) sort="Desc"; 
		if (sort.equals("asc")) sort="Assending"; 
		
		return "Get user details for User page ID = "+ pageid + " & Page limit " + limit + " And sorting = " + sort;
	}
	
	
	
	@PostMapping
	public String createUser() 
	{
		
		return "Create user details";
	}
	
	
	@PutMapping
	public String updateUser() 
	{
		
		return "Update user details";
	}
	
	@DeleteMapping
	public String deleteUser() 
	{
		
		return "Delete user details";
	}
}
