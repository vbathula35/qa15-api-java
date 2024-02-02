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
import com.example.demo.model.UserTask;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.repository.UserTaskRepository;
import com.example.demo.repository.UserTaskSpecification;
import com.example.demo.utils.GeneralUtilities;

@Service
public class UserTaskService {
	@Autowired
	private UserTaskRepository userTaskRepository;
	
	public ListResponse getAllTasks(String user, Boolean isAdmin, AllUserRequest request) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<UserTask> taskEntityList = null;
		request.setSortByCol(request.getSortByCol() == null ? new String[] {"taskDate"} : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		
		if (isAdmin) {
			taskEntityList = userTaskRepository.findAll(pageRequest);
		} else {
			taskEntityList = userTaskRepository.findAll(UserTaskSpecification.findByEmail(user), pageRequest);
		}
		
		finalRes.setPageNumber(taskEntityList.getNumber());
		finalRes.setPageSize(taskEntityList.getSize());
		finalRes.setTotal(taskEntityList.getTotalElements());
		finalRes.setOffSet(taskEntityList.getPageable().getOffset());
		finalRes.setResults(taskEntityList.getContent());
		return finalRes;
	}
	
	public ResponseEntity<?> getTaskDetails(String user, Boolean isAdmin, Integer id) throws InterruptedException, ExecutionException {
		Optional<UserTask> taskDetails = userTaskRepository.findById(id);
		if (isAdmin) {
			return new ResponseEntity<> (taskDetails, HttpStatus.OK);
		} else {
			if (taskDetails.get().getEmail().equals(user)) {
				return new ResponseEntity<> (taskDetails, HttpStatus.OK);
			} else {
				return GeneralUtilities.response("002", "User does not have permissions to view others task.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	public ResponseEntity<?> createtask(UserTask task) throws InterruptedException, ExecutionException {
		UserTask taskEntity = userTaskRepository.save(task);
		UserTask findEntity = (UserTask) userTaskRepository.findByTaskName(task.getTaskName());
		if (!(findEntity.getTaskName().isEmpty())) {
			return GeneralUtilities.response("000", "Task created successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Unable to create task at this moment. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<?> taskDone(List<Integer> tasks, Boolean isAdmin, String user) throws InterruptedException, ExecutionException {
		if (!tasks.isEmpty() || tasks.size() > 0) {
			tasks.forEach(tsk -> {
				if (isAdmin) {
					userTaskRepository.updateStatusById(tsk, AppConstant.TASK_STATUS.DONE.getValue());
				} else {
					Optional<UserTask> findEntity = userTaskRepository.findById(tsk);
					if (findEntity.get().getEmail().equals(user)) {
						userTaskRepository.updateStatusById(tsk, AppConstant.TASK_STATUS.DONE.getValue());
					}
				}
			});
			return GeneralUtilities.response("000", "task(s) mark it as done successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Please provide atlease one task.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<?> taskExpire(List<Integer> tasks, Boolean isAdmin, String user) throws InterruptedException, ExecutionException {
		if (!tasks.isEmpty() || tasks.size() > 0) {
			tasks.forEach(tsk -> {
				if (isAdmin) {
					userTaskRepository.updateStatusById(tsk, AppConstant.TASK_STATUS.EXPIRED.getValue());
				} else {
					Optional<UserTask> findEntity = userTaskRepository.findById(tsk);
					if (findEntity.get().getEmail().equals(user)) {
						userTaskRepository.updateStatusById(tsk, AppConstant.TASK_STATUS.EXPIRED.getValue());
					}
				}
			});
			return GeneralUtilities.response("000", "task(s) mark it as expired successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Please provide atlease one task.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	public ResponseEntity<?> taskDelete(List<Integer> tasks, Boolean isAdmin, String user) throws InterruptedException, ExecutionException {
		if (!tasks.isEmpty() || tasks.size() > 0) {
			tasks.forEach(tsk -> {
				if (isAdmin) {
					userTaskRepository.deleteById(tsk);;
				} else {
					Optional<UserTask> findEntity = userTaskRepository.findById(tsk);
					if (findEntity.get().getEmail().equals(user)) {
						userTaskRepository.deleteById(tsk);
					}
				}
			});
			return GeneralUtilities.response("000", "task(s) removed successfully.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Please provide atlease one task.", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
