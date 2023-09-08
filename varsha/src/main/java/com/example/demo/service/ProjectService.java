package com.example.demo.service;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelationship;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.object.UserProjectRequest;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserProjectRelationshipRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class ProjectService {
	private ProjectRepository projectRepository;
	private UserProjectRelationshipRepository userProjectRelationshipRepository;
	public ProjectService(ProjectRepository projectRepository, UserProjectRelationshipRepository userProjectRelationshipRepository) {
		this.projectRepository = projectRepository;
		this.userProjectRelationshipRepository = userProjectRelationshipRepository;
	}	
	
	
	public ListResponse getProject(String user, Boolean isAdmin, AllUserRequest request) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<Project> projectEntityList = null;
		request.setSortByCol((request.getSortByCol() == null || request.getSortByCol().isEmpty()) ? "createdDate" : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		
		if (isAdmin) {
			projectEntityList = projectRepository.findAll(pageRequest);
		} else {
			projectEntityList = projectRepository.findProjectsByEmail(user, pageRequest);
		}
		
		finalRes.setPageNumber(projectEntityList.getNumber());
		finalRes.setPageSize(projectEntityList.getSize());
		finalRes.setTotal(projectEntityList.getTotalElements());
		finalRes.setOffSet(projectEntityList.getPageable().getOffset());
		finalRes.setResults(projectEntityList.getContent());
		return finalRes;
	}
	
	
	public ResponseEntity<?> createNewProject(String user, UserProjectRequest request) throws InterruptedException, ExecutionException {
		if (!request.getProjectName().isEmpty() && !request.getProjectLocation().isEmpty()) {
			Project newProject = new Project();
			newProject.setCreatedBy(user);
			newProject.setCreatedDate(new Date());
			newProject.setProjectName(request.getProjectName());
			newProject.setProjectLocation(request.getProjectLocation());
			if (!request.getProjectDescription().isEmpty()) {
				newProject.setProjectDescription(request.getProjectDescription());
			}
			Project save = projectRepository.save(newProject);
			if (save != null) {
				UserProjectRelationship userProjectRelationship = new UserProjectRelationship();
				userProjectRelationship.setProjectId(save.getId());
				userProjectRelationship.setEmail(user);
				userProjectRelationship.setIsAdmin(true);
				UserProjectRelationship saveReltn = userProjectRelationshipRepository.save(userProjectRelationship);
				if (saveReltn != null) {
					return GeneralUtilities.response("000", "User created successfully. Please confirm with pin to activate account.", HttpStatus.OK);
				}
			}
			return GeneralUtilities.response("000", "User created successfully. Please confirm with pin to activate account.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}

};