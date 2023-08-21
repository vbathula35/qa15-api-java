package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Timesheet;
import com.example.demo.model.UserProject;
import com.example.demo.object.Response;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.object.UserProjectRequest;
import com.example.demo.repository.UserProjectRepository;
import com.example.demo.utils.GeneralUtilities;


@Service
public class UserProjectService {
	
	private UserProjectRepository userProjectRepository;
	
	public UserProjectService(UserProjectRepository userProjectRepository) {
		this.userProjectRepository = userProjectRepository;
	}
	
	
	public ResponseEntity<Response> createNewProject(String user, UserProjectRequest userProjectRequest) throws InterruptedException, ExecutionException {
		if (!userProjectRequest.getProjectName().isEmpty() && !userProjectRequest.getProjectLocation().isEmpty()) {
			UserProject userProjectEntity = new UserProject();
			userProjectEntity.setProjectName(userProjectRequest.getProjectName());
			userProjectEntity.setProjectLocation(userProjectRequest.getProjectLocation());
			if (!userProjectRequest.getProjectDescription().isEmpty()) {
				userProjectEntity.setProjectDescription(userProjectRequest.getProjectDescription());
			}
			
			userProjectRepository.saveAndFlush(userProjectEntity);
			return GeneralUtilities.response("000", "Project Created Successfully.", HttpStatus.OK);
			
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}

}
