package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.UserBo;
import com.example.demo.model.UserFeatures;
import com.example.demo.model.UserPermissions;
import com.example.demo.model.Users;
import com.example.demo.object.Response;
import com.example.demo.object.UserAuthObj;
import com.example.demo.repository.UserFeaturesRepository;
import com.example.demo.repository.UserPermissionsRepository;
import com.example.demo.repository.UsersAuthRepository;
import com.example.demo.repository.UsersRepository;
import com.google.api.client.util.Lists;

@Service
public class UserService {
	
	private UsersRepository usersRepository;
	private UsersAuthRepository usersAuthRepository;
	private UserFeaturesRepository userFeaturesRepository;
	private UserPermissionsRepository userPermissionsRepository;
	
	public UserService(UsersRepository usersRepository, 
			UsersAuthRepository usersAuthRepository, 
			UserFeaturesRepository userFeaturesRepository,
			UserPermissionsRepository userPermissionsRepository) {
		this.usersRepository = usersRepository;
		this.usersAuthRepository = usersAuthRepository;
		this.userFeaturesRepository = userFeaturesRepository; 
		this.userPermissionsRepository = userPermissionsRepository;
	}

	
	public Boolean isValidActiveUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		if (userbo.getEmail() != null && userbo.getUserStatus().equals(AppConstant.ACTIVE_USER)) {
			return true;
		}
		return false;
	}
	
	public Boolean isSuperAdminUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		if (userbo.getUserRole().equals(AppConstant.SUPER_ADMIN)) {
			return true;
		}
		return false;
	}
	
	public Boolean isAdminUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		if (userbo.getUserRole().equals(AppConstant.ADMIN)) {
			return true;
		}
		return false;
	}
	
	public Boolean isRegUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		if (userbo.getUserRole().equals(AppConstant.REG_USER)) {
			return true;
		}
		return false;
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
		userBoEntity.setPhoneNumber(userEntity.getPhoneNumber());
		userBoEntity.setUserRole(userEntity.getUserRole());
		userBoEntity.setUserStatus(userEntity.getUserAuth().getUserStatus());
		userBoEntity.setfPPin(userEntity.getUserAuth().getfPPin());
		userBoEntity.setPin(userEntity.getUserAuth().getPin());
		userBoEntity.setRegisterDate(userEntity.getUserAuth().getRegisterDate());

		userBoEntity.setUserFeatures(getUserFeaturesStringArr(userEntity.getEmail()));
		userBoEntity.setUserPermissions(getUserPermissionsStringArr(userEntity.getEmail()));
		
		return userBoEntity;
	}
	
	public List<String> getUserFeaturesStringArr(String user) {
		List<UserFeatures> userFeaturesList = userFeaturesRepository.findByEmail(user);
		List<String> featStrinArr = userFeaturesList.stream().map(f -> f.getFeatureCode()).collect(Collectors.toList());
		return featStrinArr;
		
	}
	
	public List<String> getUserPermissionsStringArr(String user) {
		List<UserPermissions> userPermissionList = userPermissionsRepository.findByEmail(user);
		List<String> permStrinArr = userPermissionList.stream().map(f -> f.getPermissionCode()).collect(Collectors.toList());
		return permStrinArr;
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
			 bo.setPhoneNumber(entity.getPhoneNumber());
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
		user.setUserRole(AppConstant.REG_USER);
		return usersRepository.save(user);
	}
	
	public void deleteUser(String email) throws InterruptedException, ExecutionException {
		usersRepository.deleteById(email);
		return;
	}
	
	
	public Object authenticate(String email, String password) throws InterruptedException, ExecutionException {
		Object[] authres = (Object[]) usersAuthRepository.loginAuthentication(email, password);
		Response res = new Response();
		if (authres != null && authres.length > 0) {
			UserAuthObj userAuthObj = new UserAuthObj();
			userAuthObj.setEmail(String.valueOf(authres[0]));
			userAuthObj.setRegisterDate(String.valueOf(authres[1]));
			userAuthObj.setUserStatus(String.valueOf(authres[2]));
	        return userAuthObj;
		}
		if (authres == null) {
			return authres;
		}
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

}
