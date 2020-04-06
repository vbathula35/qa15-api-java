package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.object.Response;
import com.example.demo.object.UserAuthObj;
import com.example.demo.object.UserCredentials;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/user")
@Api(tags = "User Unprotected Service", value = "User Unprotected Service", description = "All User unprotected services are here.")
public class UserUnprotectedController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody Users user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.registerNewUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCredentials UserCredentials, HttpServletResponse servletResponse) throws InterruptedException, ExecutionException {
		Object resp = userService.authenticate(UserCredentials.getEmail(), UserCredentials.getPassword());
		
		if (resp != null) {
			UserAuthObj response = new UserAuthObj();
			response = (UserAuthObj) resp;
			if (response.getUserStatus().equals("active")) {
				HttpHeaders headers = new HttpHeaders();
				headers.set("V-OWNER", response.getEmail());
		        headers.setExpires(8 * 60 * 60);
		       
		        
		        Cookie cookie = new Cookie("user", response.getEmail());
				cookie.setMaxAge(8 * 60 * 60);
				cookie.setPath("/");
				servletResponse.addCookie(cookie);
				
				return ResponseEntity.ok().headers(headers).body(response);
			} else {
				return new ResponseEntity<> (response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};
		Response res = new Response();
		res.setStatus("001");
		res.setDescription("Invalid credentials. Please try with valid credentials.");
		return new ResponseEntity<> (res,  HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
