package com.example.demo.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import com.example.demo.constant.AppConstant;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.Response;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.service.TimeSheetService;
import com.example.demo.service.UserService;
import com.example.demo.utils.GeneralUtilities;

@RestController
@RequestMapping("/api/user/timesheets")
@Api(tags = "User Timesheet Protected Service", value = "User Timesheet Api", description = "All User Timesheet Protected Services are here")

public class UserTimesheetController {
	
	
	@Autowired
	TimeSheetService timeSheetService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/list")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestBody TimesheetRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				try {
					return new ResponseEntity<> (timeSheetService.getWeekTimesheets(request.getEmail(), request), HttpStatus.OK);
				} catch (InterruptedException | ExecutionException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				return new ResponseEntity<> (timeSheetService.getWeekTimesheets(user, request), HttpStatus.OK);
			} catch (InterruptedException | ExecutionException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/newtimesheet")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> submitTimesheet(@CookieValue("user") String user, @RequestBody List<TimesheetRequest> request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return  timeSheetService.saveCurrentTimesheet(user, request);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	
	@PostMapping("/weeklytimesheet")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> getWeeklyTimesheets(@CookieValue("user") String user, @RequestBody TimesheetRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			try {
				return new ResponseEntity<> (timeSheetService.getWeekTimesheets(user, request), HttpStatus.OK);
			} catch (InterruptedException | ExecutionException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}

}
