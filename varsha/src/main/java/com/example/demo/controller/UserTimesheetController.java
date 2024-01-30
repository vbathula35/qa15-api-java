package com.example.demo.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import com.example.demo.constant.AppConstant;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.service.TimeSheetService;
import com.example.demo.service.UserService;
import com.example.demo.utils.GeneralUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/user/timesheets")
@Api(tags = "User Timesheet Protected Service", value = "User Timesheet Api", description = "All User Timesheet Protected Services are here")

public class UserTimesheetController {
	
	
	@Autowired
	TimeSheetService timeSheetService;
	
	@Autowired
	UserService userService;
	
	
	
	
	
	@PostMapping("/timesheets")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestBody AllUserRequest request, @RequestParam int projectId) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return new ResponseEntity<> (timeSheetService.getProjectTimesheets((userService.isSuperAdminUser(user) || userService.isAdminUser(user)), user, request, projectId), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}


	
	
	
	
	@PostMapping("/submitmonthlytimesheet")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> getWeeklyTimesheets(@CookieValue("user") String user, @RequestParam String email, @RequestParam int year, @RequestParam int month, @RequestParam int projectId, @RequestBody List<TimesheetRequest> request) throws InterruptedException, ExecutionException, JsonProcessingException, ParseException {
		if (userService.isValidActiveUser(user)) {
			String reqEmail;
			if(userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				reqEmail = email;
			} else {
				reqEmail = user;
			}
			return new ResponseEntity<> (timeSheetService.submitMonthlytimesheets(reqEmail, year, month, projectId, request), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/timesheet")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> timesheet(@CookieValue("user") String user, @RequestParam String email, @RequestParam int year, @RequestParam int month, @RequestParam int projectId) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			String reqEmail;
			if(userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				reqEmail = email;
			} else {
				reqEmail = user;
			}
			try {
				return new ResponseEntity<> (timeSheetService.timesheets(reqEmail, year, month, projectId), HttpStatus.OK);
			} catch (InterruptedException | ExecutionException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/updatetimesheet")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> timesheet(@CookieValue("user") String user, @RequestParam String email, @RequestParam int year, @RequestParam int month, @RequestParam int projectId, @RequestBody List<TimesheetRequest> request) throws InterruptedException, ExecutionException, JsonProcessingException {
		if (userService.isValidActiveUser(user)) {
			String reqEmail;
			if(userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				reqEmail = email;
			} else {
				reqEmail = user;
			}
			return new ResponseEntity<> (timeSheetService.updateTimesheets(reqEmail, year, month, projectId, request), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	

}
