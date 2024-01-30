package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.UserEvent;
import com.example.demo.model.UserTask;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.repository.UserEventRepository;
import com.example.demo.repository.UserEventSpecification;
import com.example.demo.utils.GeneralUtilities;

@Service
public class UserEventService {
	
	@Autowired
	private UserEventRepository userEventRepository;
	

	public ListResponse getAllEvents(String user, Boolean isAdmin, AllUserRequest request) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<UserEvent> taskEntityList = null;
		request.setSortByCol((request.getSortByCol() == null || request.getSortByCol().isEmpty()) ? "eventDate" : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		
		if (isAdmin) {
			taskEntityList = userEventRepository.findAll(pageRequest);
		} else {
			taskEntityList = userEventRepository.findAll(UserEventSpecification.findByEmail(user), pageRequest);
		}
		finalRes.setPageNumber(taskEntityList.getNumber());
		finalRes.setPageSize(taskEntityList.getSize());
		finalRes.setTotal(taskEntityList.getTotalElements());
		finalRes.setOffSet(taskEntityList.getPageable().getOffset());
		finalRes.setResults(taskEntityList.getContent());
		return finalRes;
	}
	
	public ResponseEntity<?> getEventDetails(String user, Boolean isAdmin, Integer id) throws InterruptedException, ExecutionException {
		Optional<UserEvent> eventDetails = userEventRepository.findById(id);
		if (isAdmin) {
			return new ResponseEntity<> (eventDetails, HttpStatus.OK);
		} else {
			if (eventDetails.get().getEmail().equals(user)) {
				return new ResponseEntity<> (eventDetails, HttpStatus.OK);
			} else {
				return GeneralUtilities.response("002", "User does not have permissions to view others event.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	public ResponseEntity<?> createEvent(UserEvent event) throws InterruptedException, ExecutionException {
		UserEvent eventEntity = userEventRepository.save(event);
		UserEvent findEntity = (UserEvent) userEventRepository.findByEventName(event.getEventName());
		if (!(findEntity.getEventName().isEmpty())) {
			return GeneralUtilities.response("000", "Event created successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Unable to create event at this moment. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> eventDone(List<Integer> events, Boolean isAdmin, String user) throws InterruptedException, ExecutionException {
		if (!events.isEmpty() || events.size() > 0) {
			events.forEach(evt -> {
				if (isAdmin) {
					userEventRepository.updateStatusById(evt, AppConstant.TASK_STATUS.DONE.getValue());
				} else {
					Optional<UserEvent> findEntity = userEventRepository.findById(evt);
					if (findEntity.get().getEmail().equals(user)) {
						userEventRepository.updateStatusById(evt, AppConstant.TASK_STATUS.DONE.getValue());
					}
				}
			});
			return GeneralUtilities.response("000", "Event(s) mark it as done successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Please provide atlease one event.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<?> eventExpire(List<Integer> events, Boolean isAdmin, String user) throws InterruptedException, ExecutionException {
		if (!events.isEmpty() || events.size() > 0) {
			events.forEach(evt -> {
				if (isAdmin) {
					userEventRepository.updateStatusById(evt, AppConstant.TASK_STATUS.EXPIRED.getValue());
				} else {
					Optional<UserEvent> findEntity = userEventRepository.findById(evt);
					if (findEntity.get().getEmail().equals(user)) {
						userEventRepository.updateStatusById(evt, AppConstant.TASK_STATUS.EXPIRED.getValue());
					}
				}
			});
			return GeneralUtilities.response("000", "Event(s) mark it as expired successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Please provide atlease one event.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<?> eventDelete(List<Integer> events, Boolean isAdmin, String user) throws InterruptedException, ExecutionException {
		if (!events.isEmpty() || events.size() > 0) {
			events.forEach(evt -> {
				if (isAdmin) {
					userEventRepository.deleteById(evt);
				} else {
					Optional<UserEvent> findEntity = userEventRepository.findById(evt);
					if (findEntity.get().getEmail().equals(user)) {
						userEventRepository.deleteById(evt);
					}
				}
			});
			return GeneralUtilities.response("000", "Event(s) removed successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Please provide atlease one event.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
