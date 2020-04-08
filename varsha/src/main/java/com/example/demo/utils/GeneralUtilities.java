package com.example.demo.utils;

import java.security.SecureRandom;

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
}
