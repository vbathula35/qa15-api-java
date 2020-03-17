package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.example.demo.model.Users;
import com.example.demo.object.User;
import com.example.demo.object.UserCredentials;
import com.example.demo.service.FirebaseService;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value = "User Api", description = "Testing User Controller")
public class UserController {
	
	@Autowired
	UserService userService;

	
	@GetMapping("/getCoockies")
	public ResponseEntity<?> login(HttpServletRequest request) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.readCookie(request), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getUser")
	public ResponseEntity<Object> getUser(@RequestHeader String email) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.getUser(email), HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getUserDetails() throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.getAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Users user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.registerNewUser(user), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody Users user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody Users user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestHeader String email) throws InterruptedException, ExecutionException {
		userService.deleteUser(email);
		return new ResponseEntity<> ("Success", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCredentials UserCredentials, HttpServletResponse response) throws InterruptedException, ExecutionException {
		String coockie = userService.authenticate(UserCredentials.getEmail(), UserCredentials.getPassword());
		return new ResponseEntity<> (coockie, HttpStatus.OK);
	}
	
	
}
