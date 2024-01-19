package com.example.demo.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Project;
import com.example.demo.model.UserFeatures;
import com.example.demo.model.UserProjectRelationship;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.object.UserProjectRequest;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserProjectRelationshipRepository;
import com.example.demo.repository.UserTimesheetRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class ProjectService {
	private ProjectRepository projectRepository;
	private UserProjectRelationshipRepository userProjectRelationshipRepository;
	private UserTimesheetRepository userTimesheetRepository;
	public ProjectService(ProjectRepository projectRepository, UserProjectRelationshipRepository userProjectRelationshipRepository, UserTimesheetRepository userTimesheetRepository) {
		this.projectRepository = projectRepository;
		this.userProjectRelationshipRepository = userProjectRelationshipRepository;
		this.userTimesheetRepository = userTimesheetRepository;
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
	
	public ResponseEntity<?> deleteProject(String user, Project request) throws InterruptedException, ExecutionException {
		if (!request.getProjectName().isEmpty() && !request.getProjectLocation().isEmpty() && (request.getId() != 0)) {
			boolean proj = projectRepository.existsById(request.getId());
			if(proj) {
				userProjectRelationshipRepository.deleteByProjectId(request.getId());
				userTimesheetRepository.deleteTimesheetsByProjectId(request.getId());
				projectRepository.delete(request);
				return GeneralUtilities.response("000", "Project deleted successfully along with Timeshe", HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable find Project", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> projectDetails(String user, int id) throws InterruptedException, ExecutionException {
		if (id != 0) {
			Optional<Project> proj = projectRepository.findById(id);
			if(!proj.isEmpty()) {
				return new ResponseEntity<> (proj, HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable find Project", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> getProjectUsers(String user, int id) throws InterruptedException, ExecutionException {
		if (id != 0) {
			List<UserProjectRelationship> users = userProjectRelationshipRepository.findAllByProjectId(id);
			if(users.size() > 0) {
				return new ResponseEntity<> (users, HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable find Users", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> addUsersToProject(String user, List<UserProjectRelationship> req) throws InterruptedException, ExecutionException {
		if (req.size() > 0) {
			 userProjectRelationshipRepository.saveAll(req);
			 return GeneralUtilities.response("000", "User added to project successfully", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> removeUsersFromProject(String user, List<UserProjectRelationship> req) throws InterruptedException, ExecutionException {
		if (req.size() > 0) {
			req.forEach(users -> {
				userProjectRelationshipRepository.deleteByEmailAndProjectId(users.getProjectId(), users.getEmail());
			});
			return GeneralUtilities.response("000", "User removed from project successfully", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}

};