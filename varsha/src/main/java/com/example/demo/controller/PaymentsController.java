package com.example.demo.controller;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.Payments;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.PaymentObject;
import com.example.demo.service.PaymentsService;
import com.example.demo.service.UserService;
import com.example.demo.utils.GeneralUtilities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/user/payments")
@Api(tags = "User Payments Protected Service", value = "User Payment Api", description = "All User Payment Protected Services are here")
public class PaymentsController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PaymentsService paymentsService;
	
	
	@PostMapping("/projectpayment")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> list(@CookieValue("user") String user, @RequestBody AllUserRequest request,@RequestParam int projectId) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				return new ResponseEntity<> (paymentsService.getPaymentsByProject(user, request, projectId), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	
	}
	
	@GetMapping("/paymentdetails")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> paymentDetails(@CookieValue("user") String user, @RequestParam int paymentId) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				return new ResponseEntity<> (paymentsService.paymentsDetils(user, paymentId), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	
	@PutMapping("/updatepayment")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> updatePayment(@CookieValue("user") String user, @RequestBody Payments request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				return new ResponseEntity<> (paymentsService.updatePayments(user, request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/newpayment")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> createPayment(@CookieValue("user") String user, @RequestBody PaymentObject request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				return new ResponseEntity<> (paymentsService.createPayment(user, request), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/deletepayment")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> deletepayment(@CookieValue("user") String user, @RequestParam int paymentId) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				return new ResponseEntity<> (paymentsService.deletePayment(user, paymentId), HttpStatus.OK);
			}
			return GeneralUtilities.response("001", AppConstant.USER_NO_PERMISSIONS, HttpStatus.UNAUTHORIZED);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	
	
	@PostMapping("/paymentdetailsByProject")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "cookie",name = "user",value = "user",required = true,dataType = "String")})
	public ResponseEntity<?> paymentdetailsByProject(@CookieValue("user") String user, @RequestBody PaymentObject request) throws InterruptedException, ExecutionException {
		if (userService.isValidActiveUser(user)) {
			if (userService.isSuperAdminUser(user) || userService.isAdminUser(user)) {
				return new ResponseEntity<> (paymentsService.paymentDetailsByProject(user, request), HttpStatus.OK);
			}
			request.setEmail(user);
			return new ResponseEntity<> (paymentsService.paymentDetailsByProject(user, request), HttpStatus.OK);
		}
		return GeneralUtilities.response("001", AppConstant.UNAUTH_USER, HttpStatus.UNAUTHORIZED);
	}
	
	


}
