package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.UserSubscriptions;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.repository.UserSubscriptionsRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class UserSubscriptionsService {
	private UserSubscriptionsRepository userSubscriptionsRepository;
	
	public UserSubscriptionsService(UserSubscriptionsRepository userSubscriptionsRepository) {
		this.userSubscriptionsRepository = userSubscriptionsRepository;
	}
	
	public Boolean findSubscriptionEmail(String email) throws InterruptedException, ExecutionException {
		UserSubscriptions findEmail = userSubscriptionsRepository.findByEmail(email);
		if (findEmail != null && !findEmail.getEmail().isEmpty() && findEmail.getEmail().equals(email)) {
			return true;
		}
		return false;
	}
	
	
	
	public ResponseEntity<Response> newSubscription(UserSubscriptions user) throws InterruptedException, ExecutionException {
		if (!findSubscriptionEmail(user.getEmail())) {
			userSubscriptionsRepository.save(user);
			if (findSubscriptionEmail(user.getEmail())) {
				return GeneralUtilities.response("000", "Email successfully subscribed.", HttpStatus.OK);
			}
			return GeneralUtilities.response("002", "Unable to subscribe at this time. Please try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return GeneralUtilities.response("001", "The email provided is already subscribed. Please try with other email.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ListResponse getAllSubscriptions(AllUserRequest request) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<UserSubscriptions> subEntityList = null;
		request.setSortByCol((request.getSortByCol() == null || request.getSortByCol().isEmpty()) ? "subscriptionDate" : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		
		subEntityList = userSubscriptionsRepository.findAll(pageRequest);
		
		finalRes.setPageNumber(subEntityList.getNumber());
		finalRes.setPageSize(subEntityList.getSize());
		finalRes.setTotal(subEntityList.getTotalElements());
		finalRes.setOffSet(subEntityList.getPageable().getOffset());
		finalRes.setResults(subEntityList.getContent());
		return finalRes;
	}
	
	public ResponseEntity<?> getSubscriptionDetails(Integer id) throws InterruptedException, ExecutionException {
		Optional<UserSubscriptions> subDetails = userSubscriptionsRepository.findById(id);
		return new ResponseEntity<> (subDetails, HttpStatus.OK);
	}
	
	public ResponseEntity<Response> subscriptionActivate(List<Integer> subs) throws InterruptedException, ExecutionException {
		if (!subs.isEmpty() || subs.size() > 0) {
			subs.forEach(sub -> {
				userSubscriptionsRepository.updateStatusById(sub, AppConstant.ACTIVE_USER);
			});
			return GeneralUtilities.response("000", "Subscription(s) mark it as activated successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provide atlease one subscription.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<Response> subscriptionDeActivate(List<Integer> subs) throws InterruptedException, ExecutionException {
		if (!subs.isEmpty() || subs.size() > 0) {
			subs.forEach(sub -> {
				userSubscriptionsRepository.updateStatusById(sub, AppConstant.INACTIVE_USER);
			});
			return GeneralUtilities.response("000", "Subscription(s) mark it as deactivated successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provide atlease one subscription.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<Response> subscriptionDelete(List<Integer> subs) throws InterruptedException, ExecutionException {
		if (!subs.isEmpty() || subs.size() > 0) {
			subs.forEach(sub -> {
				userSubscriptionsRepository.deleteById(sub);
			});
			return GeneralUtilities.response("000", "task(s) removed successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("003", "Please provide atlease one subscription.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
