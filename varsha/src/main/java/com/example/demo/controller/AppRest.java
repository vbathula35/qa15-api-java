package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/app-rest")
public class AppRest extends UserController {
	@Autowired
	UserService userService;

	@GetMapping("/rs/")
	public ResponseEntity<Object> protectedService(@RequestHeader(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		if (loginUserId.equals(null) || loginUserId.equals("")) {
			return new ResponseEntity<> ("Unauthorized user.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<> (true, HttpStatus.OK);
	}
	
	@GetMapping("/drs/")
	public ResponseEntity<Object> unProtectedService() throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (true, HttpStatus.OK);
	}
}
