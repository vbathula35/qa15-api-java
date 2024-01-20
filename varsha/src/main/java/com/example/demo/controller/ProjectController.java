package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelationship;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.object.UserProjectRelationshipObject;
import com.example.demo.object.UserProjectRequest;
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
	
	@PostMapping("/newProject")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestBody UserProjectRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isAddProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.createNewProject(user,request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	@PostMapping("/delete")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> delete(@CookieValue("user") String user, @RequestBody Project request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isAddProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.deleteProject(user,request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	@GetMapping("/details")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> details(@CookieValue("user") String user, @RequestParam Integer id) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isEditProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.projectDetails(user,id), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/userDetails")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> getProjectUserDetails(@CookieValue("user") String user, @RequestParam String email, @RequestParam int projectId) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isEditProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.getProjectUserDetails(user,email, projectId), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	@GetMapping("/users")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> getProjectUsers(@CookieValue("user") String user, @RequestParam Integer id) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isEditProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.getProjectUsers(user,id), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	
	@PostMapping("/addusers")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> addNewUsersToProject(@CookieValue("user") String user, @RequestBody List<UserProjectRelationship> request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isAddProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.addUsersToProject(user,request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	@PostMapping("/removeUsers")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> removeUsersFromProject(@CookieValue("user") String user, @RequestBody List<UserProjectRelationship> request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isAddProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.removeUsersFromProject(user,request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	@PutMapping("/updateUserDetails")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> removeUsersFromProject(@CookieValue("user") String user, @RequestBody UserProjectRelationshipObject request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isAddProjectPermission(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (projectService.updateUserInProject(user,request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
}
