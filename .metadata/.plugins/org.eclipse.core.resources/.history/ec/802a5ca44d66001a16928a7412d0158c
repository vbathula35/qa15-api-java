package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.object.User;
import com.example.demo.service.FirebaseService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value = "User Api", description = "Testing User Controller")
public class UserController {
	
	private static Map<String, User> userRepo = new HashMap<>();
	
	static {
		User venkat = new User();
		venkat.setFirstName("Venkat");
		venkat.setLastName("Reddy");
		venkat.setEmail("venkattest@gmail.com");
		venkat.setPassword("Admin@123");
		userRepo.put(venkat.getEmail(), venkat);
      
		User shekar = new User();
		shekar.setFirstName("Shekar");
		shekar.setLastName("Reddy");
		shekar.setEmail("shekartest@gmail.com");
		shekar.setPassword("Admin@123");
		userRepo.put(shekar.getEmail(), shekar);
	}
	
	@Autowired
	FirebaseService firebaseService;
	
	@GetMapping("/getUsers")
	public ResponseEntity<Object> getUserDetails() throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (firebaseService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/getUserDetails")
	public ResponseEntity<Object> getUserDetails(@RequestHeader String email) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (firebaseService.getUserDetails(email), HttpStatus.OK);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (firebaseService.createNewUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (firebaseService.updateUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Object> deleteUser(@RequestHeader String email) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (firebaseService.deleteUser(email), HttpStatus.OK);
	}
}
