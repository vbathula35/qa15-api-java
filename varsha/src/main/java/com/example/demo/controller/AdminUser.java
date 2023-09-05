package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.Users;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.Response;
import com.example.demo.object.UserFeaturePermissions;
import com.example.demo.service.UserService;
import com.example.demo.utils.GeneralUtilities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/admin/user")
@Api(tags = "Admin User Protected Service", value = "Admin User Api", description = "All Admin user Protected Services are here")
public class AdminUser {
	@Autowired
	UserService userService;
	
	
	@PostMapping("/getAllUsers")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> getAllUsers(@CookieValue("user") String user, @RequestBody AllUserRequest request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (userService.getAllUsers(request), HttpStatus.OK);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getUserDetails")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> getUserDetails(@CookieValue("user") String user, @RequestParam("email") String email) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return new ResponseEntity<> (userService.getUser(email), HttpStatus.OK);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/updateUserFeaturePermissions")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> updateUserFeaturePermissions(@CookieValue("user") String user, @RequestBody UserFeaturePermissions updateUser) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return userService.updateUserFeaturePermissions(updateUser);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@RequestBody Users user, @CookieValue(value = "V-OWNER") final String loginUserId) throws InterruptedException, ExecutionException {
		return new ResponseEntity<> (userService.createNewUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/activateUsers")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> activateUsers(@CookieValue("user") String user, @RequestBody List<String> users) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return userService.activateUsers(users);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PutMapping("/deActivateUsers")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> deActivateUsers(@CookieValue("user") String user, @RequestBody List<String> users) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return userService.deActivateUsers(users);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	
	@PostMapping("/deleteUsers")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<Response> deleteUsers(@CookieValue("user") String user, @RequestBody List<String> users) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				return userService.deleteUser(users);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/upload")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> UploadUsers(@CookieValue("user") String user, @RequestParam("file") MultipartFile file) throws InterruptedException, ExecutionException, IOException {
		if (userService.isValidActiveUser(user)) {
			if(userService.isAdminUser(user) || userService.isSuperAdminUser(user)) {
				String type = file.getOriginalFilename().split("\\.")[1];
		        InputStream inputStream = file.getInputStream();
		        try {
		        	if (type == "txtFile") {
		        		byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			            String data = new String(bdata, StandardCharsets.UTF_8);
			            List<String> json = Stream.of(data.split(";", -1))
			            		  .collect(Collectors.toList());
			            System.out.print("File Type" + type);
			            return new ResponseEntity<> (json, HttpStatus.OK);
		        	} else if (type == "xlsx") {
		        		
		        	} else {
		        		return GeneralUtilities.response("003", "File Not Supported", HttpStatus.BAD_REQUEST);
		        	}
		            
		        } catch (IOException e) {
		        	System.out.print("IOException" + e);
		        }
		        
//				return GeneralUtilities.response("000", "successfully.", HttpStatus.OK);
			}
			return GeneralUtilities.response("002", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	
}
