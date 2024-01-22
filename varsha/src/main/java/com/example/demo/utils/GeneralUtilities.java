package com.example.demo.utils;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.object.Response;

public class GeneralUtilities {
	
	public static ResponseEntity<Response> response(String status, String des, HttpStatus httpStatus) {
		Response res = new Response();
		res.setStatus(status);
		res.setDescription(des);
		return new ResponseEntity<Response> (res, httpStatus);
	}
	
	public static String valueEncoder(String val) {
		int strength = 10;
		String endoced  = new BCryptPasswordEncoder(strength, new SecureRandom()).encode(val);
	    return endoced;
	}
	
	public static boolean compareBCryptValue(String val, String encodedPassword) {
		int strength = 10;
		Boolean status = new BCryptPasswordEncoder(strength, new SecureRandom()).matches(val, encodedPassword);
		return status;
	}
	
	public static Direction sortDirection(String value) {
		return   value.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
	}
	
}
