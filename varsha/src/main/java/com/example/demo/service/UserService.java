package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.Email;
import com.example.demo.model.UserAuth;
import com.example.demo.model.UserBo;
import com.example.demo.model.UserFeatures;
import com.example.demo.model.UserPermissions;
import com.example.demo.model.Users;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ChangePassword;
import com.example.demo.object.ListResponse;
import com.example.demo.object.PinValidation;
import com.example.demo.object.Response;
import com.example.demo.object.User;
import com.example.demo.object.UserAuthObj;
import com.example.demo.object.UserFeaturePermissions;
import com.example.demo.object.UsersList;
import com.example.demo.repository.UserFeaturesRepository;
import com.example.demo.repository.UserPermissionsRepository;
import com.example.demo.repository.UserSpecification;
import com.example.demo.repository.UsersAuthRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class UserService {
	
	Response serviceRes = new Response();
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	AuthorizationService authorizationService;
	
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
		return (userbo.getEmail() != null && userbo.getUserStatus().equals(AppConstant.ACTIVE_USER)) ? true : false;
	}
	
	public Boolean isSuperAdminUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		return userbo.getUserRole().equals(AppConstant.SUPER_ADMIN) ? true : false;
	}
	
	public Boolean isAdminUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		return userbo.getUserRole().equals(AppConstant.ADMIN) ? true : false;
	}
	
	public Boolean isRegUser(String user) throws InterruptedException, ExecutionException {
		UserBo userbo = getUser(user);
		return userbo.getUserRole().equals(AppConstant.REG_USER) ? true : false;
	}
	
	public Boolean userPermisssion(String user, String permission ) throws InterruptedException, ExecutionException {
		List<String> userPermissions = getUserPermissionsStringArr(user);
		return userPermissions.contains(permission);
	}
	
	public Boolean isAddProjectPermission(String user) throws InterruptedException, ExecutionException{
		return userPermisssion(user, AppConstant.USER_PERMISSIONS.AddProject.getValue());
	}
	
	public Boolean isEditProjectPermission(String user) throws InterruptedException, ExecutionException{
		return userPermisssion(user, AppConstant.USER_PERMISSIONS.EditProject.getValue());
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


	
	public ListResponse getAllUsers(AllUserRequest request) throws InterruptedException, ExecutionException {
		
		ListResponse finalRes = new ListResponse();
		Page<Users> userEntityList = null;
		request.setSortByCol((request.getSortByCol() == null || request.getSortByCol().isEmpty()) ? "firstName" : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "ASC" : request.getSortByDirection());
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		if (request.getFilterBy() == null) {
			userEntityList = usersRepository.findAll(pageRequest);
		} else {
			switch (request.getFilterBy()) {
				case "firstName":
					userEntityList = usersRepository.findAll(UserSpecification.findAllUsersByFirstName(request.getFilterValue()) ,pageRequest );
					break;
				case "lastName":
					userEntityList = usersRepository.findAll(UserSpecification.findAllUsersByLastName(request.getFilterValue()) ,pageRequest );
					break;
				case "email":
					userEntityList = usersRepository.findAll(UserSpecification.findAllUsersByEmail(request.getFilterValue()) ,pageRequest );
					break;
				case "userRole":
					userEntityList = usersRepository.findAll(UserSpecification.findAllUsersByUserRole(request.getFilterValue()) ,pageRequest );
					break;
				case "userStatus":
//					userEntityList = usersRepository.findAll(UserSpecification.findAllUsersByUserStatus(request.getFilterValue()) ,pageRequest );
					break;
				default:
					userEntityList = usersRepository.findAll(pageRequest);
		            break;
			}
		}
		finalRes.setPageNumber(userEntityList.getNumber());
		finalRes.setPageSize(userEntityList.getSize());
		finalRes.setTotal(userEntityList.getTotalElements());
		finalRes.setOffSet(userEntityList.getPageable().getOffset());
		List<Object> usersList = new ArrayList<>();
		 for (Users entity : userEntityList.getContent()) {
			 UsersList usr = new UsersList();
			 usr.setEmail(entity.getEmail());
			 usr.setFirstName(entity.getFirstName());
			 usr.setLastName(entity.getLastName());
			 usr.setUserRole(entity.getUserRole());
			 usr.setUserStatus(entity.getUserAuth().getUserStatus());
			 usersList.add(usr);
		}	
		finalRes.setResults(usersList);
		return finalRes;
	}
	
	


	public Users createNewUser(Users user) throws InterruptedException, ExecutionException {
		return usersRepository.save(user);
	}
	
	public ResponseEntity<Response> registerNewUser(User user) throws InterruptedException, ExecutionException {
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
					
					List<String> roleFeatures = authorizationService.getRoleFeaturesStringArr(AppConstant.REG_USER);
					List<String> rolePermissions = authorizationService.getRolePermissionsStringArr(AppConstant.REG_USER);
					List<UserFeatures> userFeatures = constrctUserFeatureArray(roleFeatures, reValidateUserEntity.get().getEmail());
					List<UserPermissions> userPermissions = constructionUserPermissionsArray(rolePermissions, reValidateUserEntity.get().getEmail());
					
					userFeaturesRepository.saveAll(userFeatures);
					userPermissionsRepository.saveAll(userPermissions);
					
					String subject = "Varsha pin verification";
					String message = "Hi, Your verification PIN:"+ number +". Verify your pin to activate Varsha account.";
							
					emailService.sendMail(reValidateUserEntity.get().getEmail(), subject, message);
					return GeneralUtilities.response("000", "User created successfully. Please confirm with pin to activate account.", HttpStatus.OK);
				}
			}
		}
		return GeneralUtilities.response("001", "Email Id already exist in our records. Please try with different email id.", HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	public List<UserFeatures> constrctUserFeatureArray(List<String> features, String email) {
		List<UserFeatures> userFeaturesArry = new ArrayList<>();
		features.forEach(feat -> {
			UserFeatures userFeature = new UserFeatures();
			userFeature.setEmail(email);
			userFeature.setFeatureCode(feat);
			userFeaturesArry.add(userFeature);
		});
		return userFeaturesArry;
	}
	
	public List<UserPermissions> constructionUserPermissionsArray(List<String> permissions, String email) {
		List<UserPermissions> userPermissionsArry = new ArrayList<>();
		permissions.forEach(perm -> {
			UserPermissions userPerm= new UserPermissions();
			userPerm.setEmail(email);
			userPerm.setPermissionCode(perm);
			userPermissionsArry.add(userPerm);
		});
		return userPermissionsArry;
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
	
	public ResponseEntity<Response> updateUserFeaturePermissions(UserFeaturePermissions user) throws InterruptedException, ExecutionException {
		if (!user.getEmail().isEmpty() && user.getFeatures().size() > 0 && user.getPermissions().size() > 0) {
			userFeaturesRepository.deleteByEmail(user.getEmail());
			userPermissionsRepository.deleteByEmail(user.getEmail());
			List<UserFeatures> userFeatures = constrctUserFeatureArray(user.getFeatures(), user.getEmail());
			List<UserPermissions> userPermissions = constructionUserPermissionsArray(user.getPermissions(), user.getEmail());			
			userFeaturesRepository.saveAll(userFeatures);
			userPermissionsRepository.saveAll(userPermissions);
			return GeneralUtilities.response("000", "User features and permissions are updated successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provider required fields and assign atleaset one feature and permission.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	
	public ResponseEntity<Response> deActivateUsers(List<String> users) throws InterruptedException, ExecutionException {
		if (!users.isEmpty() || users.size() > 0) {
			users.forEach(user -> {
				usersAuthRepository.saveUserStatusByEmail(user, AppConstant.INACTIVE_USER);
			});
			return GeneralUtilities.response("000", "User(s) de activated successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provide atlease one user.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<Response> activateUsers(List<String> users) throws InterruptedException, ExecutionException {
		if (!users.isEmpty() || users.size() > 0) {
			users.forEach(user -> {
				usersAuthRepository.saveUserStatusByEmail(user, AppConstant.ACTIVE_USER);
			});
			return GeneralUtilities.response("000", "User(s) activated successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provide atlease one user.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<Response> deleteUser(List<String> users) throws InterruptedException, ExecutionException {
		if (!users.isEmpty() || users.size() > 0) {
			users.forEach(user -> {
				usersAuthRepository.deleteById(user);
				usersRepository.deleteById(user);
			});
			return GeneralUtilities.response("000", "User(s) deleted successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provide atlease one user.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Response> changePassword(ChangePassword user) throws InterruptedException, ExecutionException {
		Optional<UserAuth> findUsr = usersAuthRepository.findById(user.getEmail());
		if (findUsr.get().getEmail() != null && !findUsr.get().getEmail().isEmpty()) {
			if(GeneralUtilities.compareBCryptValue(user.getCurrentPassword(), findUsr.get().getPassword())) {
				String password = GeneralUtilities.valueEncoder(user.getPassword());
				UserAuth usrAuth = new UserAuth();
				usrAuth.setEmail(user.getEmail());
				usrAuth.setPassword(password);
				usrAuth.setRegisterDate(findUsr.get().getRegisterDate());
				usrAuth.setUserStatus(findUsr.get().getUserStatus());
				usrAuth.setPin(findUsr.get().getPin());
				usersAuthRepository.save(usrAuth);
				return GeneralUtilities.response("000", "Your password changed succefully.", HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Your current password does not match with our records. Please enter correct password.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return GeneralUtilities.response("002", "User not found in our records.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Response> forgotPassword(Email email) throws InterruptedException, ExecutionException {
		UserAuth userVal = usersAuthRepository.findById(email.getEmail()).orElse(new UserAuth());
		if (userVal.getEmail() != null && !userVal.getEmail().isEmpty()) {
			long fpPin = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			userVal.setfPPin(fpPin);
			userVal.setUserStatus(AppConstant.RESET_PASSWORD);
			usersAuthRepository.save(userVal);
			String subject = "Varsha pin verification";
			String message = "Hi, Your verification PIN:"+ fpPin +". Verify your pin to reset your password.";
			emailService.sendMail(userVal.getEmail(), subject, message);
			return GeneralUtilities.response("000", "Verification pin sent to your email. Please verify your pin.", HttpStatus.OK);
		}
		return GeneralUtilities.response("001", "Not a valid user. Please try with valid user.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Response> resetPassword(UserAuth user) throws InterruptedException, ExecutionException {
		UserAuth userVal = usersAuthRepository.findById(user.getEmail()).orElse(new UserAuth());
		if (userVal.getEmail() != null && !userVal.getEmail().isEmpty()) {
			if (userVal.getfPPin().equals(user.getfPPin())) {
				userVal.setUserStatus(AppConstant.ACTIVE_USER);
				userVal.setPassword(GeneralUtilities.valueEncoder(user.getPassword()));
				usersAuthRepository.save(userVal);
				String subject = "Varsha pin verification";
				String message = "Hi, Your successfully verified your pin. Your password updated successfully.";
				emailService.sendMail(userVal.getEmail(), subject, message);
				return GeneralUtilities.response("000", "Verification pin sent to your email. Please verify your pin.", HttpStatus.OK);
			}
			return GeneralUtilities.response("002", "Provided pin is not matching with our records. Please try with valid pin.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return GeneralUtilities.response("001", "Not a valid user. Please try with valid user.", HttpStatus.INTERNAL_SERVER_ERROR);
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
