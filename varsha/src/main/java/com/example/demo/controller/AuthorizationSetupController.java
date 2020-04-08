package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.AppConstant;
import com.example.demo.object.Response;
import com.example.demo.service.AuthorizationService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/authorization")
@Api(tags = "Authorization Setup Service", value = "Authorization Api", description = "All authorization Protected Services are here")
public class AuthorizationSetupController {
	Response errRes = new Response();
	
	@Autowired
	AuthorizationService authorizationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	
	@GetMapping("/getAllRole")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getAllUsers(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getAllRoles(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getAllFeatures")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getAllFeatures(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getAllFeatures(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getAllPermissions")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getAllPermissions(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getAllPermissions(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getActiveRole")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getActiveRole(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getActiveRoles(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getActiveFeatures")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getActiveFeatures(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getActiveFeatures(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getActivePermissions")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getActivePermissions(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getActivePermissions(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getAllRolesFeaturesPermissions")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> getAllRolesFeaturesPermissions(@CookieValue("user") String user) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (authorizationService.getAllRolesFeaturesPermissions(), HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	
	@PostMapping("/sendEmail")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Object> sendEmail(@CookieValue("user") String user, @RequestParam String senderEmail, @RequestParam String subject, @RequestParam String message) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isSuperAdminUser(user)) {
				String email = emailService.sendMail(senderEmail, subject, message);
				return new ResponseEntity<> (email, HttpStatus.OK);
			}
			errRes.setStatus("002");
	        errRes.setDescription(AppConstant.USER_NO_PERMISSIONS);
			return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
		}
		errRes.setStatus("001");
        errRes.setDescription(AppConstant.UNAUTH_USER);
		return new ResponseEntity<> (errRes, HttpStatus.UNAUTHORIZED);
	}
	
	
	
}
