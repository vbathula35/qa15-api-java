package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.object.Response;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/user")
@Api(tags = "User Protected Service", value = "User Api", description = "All user Protected Services are here")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getCoockies")
	public ResponseEntity<?> getCoockies(HttpServletRequest request) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.readCookie(request), HttpStatus.OK);
	}

	@GetMapping("/getUser")
	public ResponseEntity<Object> getUser(@RequestHeader(name = "V-OWNER", required = true) String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.getUser(loginUserId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getAllUsers(@CookieValue(value = "V-OWNER", defaultValue = "unknown") final String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.getAllUsers(), HttpStatus.OK);
	}
	
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody Users user, @CookieValue(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody Users user, @CookieValue(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestBody String email, @CookieValue(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		userService.deleteUser(email);
		return new ResponseEntity<> ("Success", HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(@CookieValue(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
//		headers.remove(HttpHeaders.CONNECTION);
//        headers.remove(HttpHeaders.CONTENT_TYPE);
//        headers.remove(HttpHeaders.CONTENT_LENGTH);
//        headers.remove("V-OWNER");
        headers.clear();
        headers.clearContentHeaders();
		Response res = new Response();
		res.setStatus("000");
		res.setDescription("Successfully logged out");
		return new ResponseEntity<> (res, headers,  HttpStatus.OK);
	}
	
	
}
