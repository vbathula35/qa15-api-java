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
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;
import com.example.demo.utils.GeneralUtilities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/user/project")
@Api(tags = "User Project Protected Service", value = "User Project Api", description = "All User Project Protected Services are here")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/list")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestBody AllUserRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return new ResponseEntity<> (projectService.getProject(user, userService.isAdminUser(user) || userService.isSuperAdminUser(user), request), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
}
