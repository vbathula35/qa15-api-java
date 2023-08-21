package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.AppConstant;
import com.example.demo.object.UserProjectRequest;
import com.example.demo.service.UserProjectService;
import com.example.demo.service.UserService;
import com.example.demo.utils.GeneralUtilities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;



@RestController
@RequestMapping("/api/user/projects")
@Api(tags = "User Project Protected Service", value = "User Project Api", description = "All User Project Protected Services are here")
public class UserProjectController {
	
	
	
	@Autowired
	UserProjectService userProjectService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createproject")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> createProject(@CookieValue("user") String user, @RequestBody UserProjectRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return userProjectService.createNewProject(user, request);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
}
