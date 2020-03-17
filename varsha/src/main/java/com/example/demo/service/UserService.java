package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserBo;
import com.example.demo.model.Users;
import com.example.demo.repository.UserAuthRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class UserService {
	
	private UsersRepository usersRepository;
	public UserService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	
	public UserBo getUser(String user) throws InterruptedException, ExecutionException {
		Users userEntity = usersRepository.findById(user).get();
		UserBo userBoEntity = new UserBo();
		userBoEntity.setEmail(userEntity.getEmail());
		userBoEntity.setFirstName(userEntity.getFirstName());
		userBoEntity.setLastName(userEntity.getLastName());
		userBoEntity.setAddressLine1(userEntity.getAddressLine1());
		userBoEntity.setAddressLine2(userEntity.getAddressLine2());
		userBoEntity.setCity(userEntity.getCity());
		userBoEntity.setState(userEntity.getState());
		userBoEntity.setZipCode(userEntity.getZipCode());
		userBoEntity.setCountry(userEntity.getCountry());
		userBoEntity.setUserRole(userEntity.getUserRole());
		userBoEntity.setUserStatus(userEntity.getUserAuth().getUserStatus());
		userBoEntity.setfPPin(userEntity.getUserAuth().getfPPin());
		userBoEntity.setPin(userEntity.getUserAuth().getPin());
		userBoEntity.setRegisterDate(userEntity.getUserAuth().getRegisterDate());
		return userBoEntity;
	}
	
	public List<UserBo> getAllUsers() throws InterruptedException, ExecutionException {
		 List<Users> userEntityList = usersRepository.findAll();
		 List<UserBo> boList = new ArrayList<>();
		 for (Users entity : userEntityList) {
			 UserBo bo = new UserBo();
			 bo.setEmail(entity.getEmail());
			 bo.setFirstName(entity.getFirstName());
			 bo.setLastName(entity.getLastName());
			 bo.setAddressLine1(entity.getAddressLine1());
			 bo.setAddressLine2(entity.getAddressLine2());
			 bo.setCity(entity.getCity());
			 bo.setState(entity.getState());
			 bo.setZipCode(entity.getZipCode());
			 bo.setCountry(entity.getCountry());
			 bo.setUserRole(entity.getUserRole());
			 bo.setUserStatus(entity.getUserAuth().getUserStatus());
			 bo.setfPPin(entity.getUserAuth().getfPPin());
			 bo.setPin(entity.getUserAuth().getPin());
			 bo.setRegisterDate(entity.getUserAuth().getRegisterDate());
			 boList.add(bo);
		}
		 
		 return boList;
	}
	
	public Users createNewUser(Users user) throws InterruptedException, ExecutionException {
		return usersRepository.save(user);
	}
	
	public Users registerNewUser(Users user) throws InterruptedException, ExecutionException {
		user.setUserRole("REGUSR");
		return usersRepository.save(user);
	}
	
	public void deleteUser(String email) throws InterruptedException, ExecutionException {
		usersRepository.deleteById(email);
		return;
	}
	
	
	public String authenticate(String email, String password) throws InterruptedException, ExecutionException {
		String authres = usersRepository.loginAuthentication(email, password);
//		Response res = new Response();
//		if (authres != null || authres != "") {
//			HttpHeaders headers = new HttpHeaders();
//	        headers.add("Set-Cookie","V-OWNER="+authres);
//	        headers.setExpires(8 * 60 * 60);
//	        ResponseEntity.status(HttpStatus.OK).headers(headers).build();
//			res.setStatus("000");
//			res.setDescription("Success");
//	        return res;
//		}
//		res.setStatus("001");
//		res.setDescription("Authentication Failed");
		return authres;
	}
	
	public String readCookie(HttpServletRequest request) throws InterruptedException, ExecutionException {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        return Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }

	    return "No cookies";
	}
	
	public HttpHeaders logout() throws InterruptedException, ExecutionException {
		HttpHeaders headers = new HttpHeaders();
		headers.remove("V-OWNER");
//		Response res = new Response();
//		res.setStatus("001");
//		res.setDescription("Authentication Failed");
		return headers;
	}

}
