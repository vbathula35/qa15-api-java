package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.UserAuth;
import com.example.demo.model.UserBo;
import com.example.demo.model.UserFeatures;
import com.example.demo.model.UserPermissions;
import com.example.demo.model.Users;
import com.example.demo.object.PinValidation;
import com.example.demo.object.Response;
import com.example.demo.object.User;
import com.example.demo.object.UserAuthObj;
import com.example.demo.repository.UserFeaturesRepository;
import com.example.demo.repository.UserPermissionsRepository;
import com.example.demo.repository.UsersAuthRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class UserService {
	
	Response serviceRes = new Response();
	
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
		Optional<Users> validateUser = getUserEntity(user);
		Users userEntity = validateUser.get();
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
	
	public Response registerNewUser(User user) throws InterruptedException, ExecutionException {
		Optional<Users> validateUserEntity = getUserEntity(user.getEmail());
		if(!validateUserEntity.isPresent()) {
			String password = GeneralUtilities.valueEncoder(user.getPassword());
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			
			UserAuth userAuthEntity = new UserAuth();
			userAuthEntity.setEmail(user.getEmail());
			userAuthEntity.setRegisterDate(new Date());
			userAuthEntity.setPassword(password);
			userAuthEntity.setUserStatus(AppConstant.PENDING_USER);
			userAuthEntity.setPin(number);
			usersAuthRepository.save(userAuthEntity);
			
			Optional<UserAuth> validateUserAuthEntity = getUserAuthEntity(user.getEmail());
			
			if (validateUserAuthEntity.isPresent() && !validateUserAuthEntity.get().getEmail().isEmpty()) {
				Users newUserEntity = new Users();
				newUserEntity.setEmail(user.getEmail());
				newUserEntity.setFirstName(user.getFirstName());
				newUserEntity.setLastName(user.getLastName());
				newUserEntity.setUserRole(AppConstant.REG_USER);
				newUserEntity.setAddressLine1(user.getAddressLine1());
				newUserEntity.setAddressLine2(user.getAddressLine2());
				newUserEntity.setCity(user.getCity());
				newUserEntity.setState(user.getState());
				newUserEntity.setZipCode(user.getZipCode());
				newUserEntity.setCountry(AppConstant.COUNTRY);
				newUserEntity.setPhoneNumber(user.getPhoneNumber());
				usersRepository.save(newUserEntity);
				
				Optional<Users> reValidateUserEntity = getUserEntity(user.getEmail());
				
				if (reValidateUserEntity.isPresent() && !reValidateUserEntity.get().getEmail().isEmpty()) {
					serviceRes.setStatus("000");
					serviceRes.setDescription("Successfully registered. Please login with your credentials.");
					return serviceRes;
				}
			}
		}
		
		serviceRes.setStatus("001");
		serviceRes.setDescription("Email Id already exist in our records. Please try with different email id.");
		return serviceRes;
	}
	
	public void deleteUser(String email) throws InterruptedException, ExecutionException {
		usersRepository.deleteById(email);
		return;
	}
	
	
	public Object authenticate(String email, String password) throws InterruptedException, ExecutionException {
		
		Optional<UserAuth> validateUserAuthEntity = getUserAuthEntity(email);
		if (validateUserAuthEntity.isPresent()) {
			if (GeneralUtilities.compareBCryptValue(password, validateUserAuthEntity.get().getPassword())) {
				UserAuthObj userAuthObj = new UserAuthObj();
				userAuthObj.setEmail(validateUserAuthEntity.get().getEmail());
				userAuthObj.setRegisterDate(validateUserAuthEntity.get().getRegisterDate());
				userAuthObj.setUserStatus(validateUserAuthEntity.get().getUserStatus());
				return userAuthObj;
			}
			return null;
		}
		return null;
	}
	
	
	private Optional<Users> getUserEntity(String email) {
		return usersRepository.findById(email);
	}
	
	private Optional<UserAuth> getUserAuthEntity(String email) {
		return usersAuthRepository.findById(email);
	}
	
	
	public ResponseEntity<Response> activateUser(PinValidation value) throws InterruptedException, ExecutionException {
		Optional<UserAuth> validateUserAuthEntity = getUserAuthEntity(value.getEmail());
		if (validateUserAuthEntity.isPresent()) {
			UserAuth userAuth = validateUserAuthEntity.get();
			if (!String.valueOf(value.getPin()).isEmpty() && value.getPin().equals(userAuth.getPin())) {
				userAuth.setUserStatus(AppConstant.ACTIVE_USER);
				usersAuthRepository.save(userAuth);
				return  GeneralUtilities.response("000", "User activated successfully. Please login with credentials.", HttpStatus.OK );
			}
			return GeneralUtilities.response("002", "User pin not matching with our records. Please try with valid pin.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return GeneralUtilities.response("001", "User not found in our records. Please try with valida user.", HttpStatus.INTERNAL_SERVER_ERROR);
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
