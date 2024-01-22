package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ChangePassword;
import com.example.demo.object.Response;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/user")
@Api(tags = "User Protected Service", value = "User Api", description = "All user Protected Services are here")
public class UserController {
	Response errRes = new Response();
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getCoockies")
	public ResponseEntity<?> getCoockies(HttpServletRequest request) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.readCookie(request), HttpStatus.OK);
	}

	@GetMapping("/getUser")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getUser(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.getUser(user), HttpStatus.OK);
	}
	

	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> getAllUsers(@RequestBody AllUserRequest allUserRequest, @CookieValue(value = "V-OWNER") String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.getAllUsers(allUserRequest), HttpStatus.OK);
	}
	
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody Users user, @CookieValue(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> updateUser(@RequestBody Users user, @CookieValue("user") String loggedinUser) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.OK);
	}
	
	@PostMapping("/changepassword")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> changePassword(@RequestBody ChangePassword user, @CookieValue("user") String loggedinUser) throws InterruptedException, ExecutionException {
		return userService.changePassword(user);
	}
	
	@GetMapping("/logout")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> logout(@CookieValue("user") String user, HttpServletResponse servletResponse) throws InterruptedException, ExecutionException {
		Cookie cookie = new Cookie("user", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		servletResponse.addCookie(cookie);
		
		
        errRes.setStatus("000");
        errRes.setDescription("Successfully logged out");
		return new ResponseEntity<> (errRes, HttpStatus.OK);
	}

}
