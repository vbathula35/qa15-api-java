package com.example.demo.controller;

import java.util.Date;
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

import com.example.demo.constant.AppConstant;
import com.example.demo.model.Email;
import com.example.demo.model.UserAuth;
import com.example.demo.model.UserSubscriptions;
import com.example.demo.object.PinValidation;
import com.example.demo.object.Response;
import com.example.demo.object.User;
import com.example.demo.object.UserAuthObj;
import com.example.demo.object.UserCredentials;
import com.example.demo.service.UserService;
import com.example.demo.service.UserSubscriptionsService;
import com.example.demo.utils.GeneralUtilities;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/user")
@Api(tags = "User Unprotected Service", value = "User Unprotected Service", description = "All User unprotected services are here.")
public class UserUnprotectedController {
	@Autowired
	UserService userService;
	@Autowired
	UserSubscriptionsService userSubscriptionsService;
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody UserSubscriptions data) throws InterruptedException, ExecutionException {
		if (!userSubscriptionsService.findSubscriptionEmail(data.getEmail())) {
			UserSubscriptions user = new UserSubscriptions();
			user.setEmail(data.getEmail());
			user.setSubscriptionDate(new Date());
			user.setStatus(AppConstant.ACTIVE_USER);
			return userSubscriptionsService.newSubscription(user);
		}
		return GeneralUtilities.response("001", "The email provided is already subscribed. Please try with other email.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/activate")
	public ResponseEntity<?> activate(@RequestBody PinValidation userPin) throws InterruptedException, ExecutionException {
		return userService.activateUser(userPin);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody Email user) throws InterruptedException, ExecutionException {
		return userService.forgotPassword(user);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody UserAuth user) throws InterruptedException, ExecutionException {
		return userService.resetPassword(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.registerNewUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCredentials UserCredentials, HttpServletResponse servletResponse) throws InterruptedException, ExecutionException {
		Object resp = userService.authenticate(UserCredentials.getEmail(), UserCredentials.getPassword());
		if (resp != null) {
			UserAuthObj response = new UserAuthObj();
			response = (UserAuthObj) resp;
			if (response.getUserStatus().equals(AppConstant.ACTIVE_USER)) {
				HttpHeaders headers = new HttpHeaders();
				headers.set("V-OWNER", response.getEmail());
		        headers.setExpires(8 * 60 * 60);
		       
		        
		        Cookie cookie = new Cookie("user", response.getEmail());
				cookie.setMaxAge(8 * 60 * 60);
				cookie.setPath("/");
				servletResponse.addCookie(cookie);
				
				return ResponseEntity.ok().headers(headers).body(response);
			} else {
				return new ResponseEntity<> (response, HttpStatus.OK);
			}
		};
		return GeneralUtilities.response("001", "Invalid credentials. Please try with valid credentials.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
