package com.example.demo.controller;

import java.util.Date;
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

import com.example.demo.constant.AppConstant;
import com.example.demo.model.UserTask;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.Response;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTaskService;
import com.example.demo.utils.GeneralUtilities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


@RestController
@RequestMapping("/api/user/task")
@Api(tags = "User Task Protected Service", value = "User Task Api", description = "All User Task Protected Services are here")
public class UserTaskController {
	@Autowired
	UserTaskService userTaskService;
	
	@Autowired
	UserService userService;
	

	@PostMapping("/list")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestBody AllUserRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return new ResponseEntity<> (userTaskService.getAllTasks(user, (userService.isAdminUser(user) || userService.isSuperAdminUser(user)),request), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/details")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestParam Integer id) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return new ResponseEntity<> (userTaskService.getTaskDetails(user, userService.isAdminUser(user) || userService.isSuperAdminUser(user),id), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/create")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> list(@CookieValue("user") String user, @RequestBody UserTask task) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			task.setStatus(AppConstant.TASK_STATUS.PENDING.getValue());
			task.setCreatedDate(new Date());
			if (!(userService.isAdminUser(user) || userService.isSuperAdminUser(user))) {
				task.setEmail(user);
			}
			return userTaskService.createtask(task);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	
	@PutMapping("/done")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> taskDone(@CookieValue("user") String user, @RequestBody List<Integer> tasks) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return userTaskService.taskDone(tasks, (userService.isAdminUser(user) || userService.isSuperAdminUser(user)), user);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/expire")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> taskExpire(@CookieValue("user") String user, @RequestBody List<Integer> tasks) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return userTaskService.taskExpire(tasks, (userService.isAdminUser(user) || userService.isSuperAdminUser(user)), user);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/delete")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> taskDelete(@CookieValue("user") String user, @RequestBody List<Integer> tasks) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			return userTaskService.taskDelete(tasks, (userService.isAdminUser(user) || userService.isSuperAdminUser(user)), user);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
}

