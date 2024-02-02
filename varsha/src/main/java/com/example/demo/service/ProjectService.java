package com.example.demo.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

import com.example.demo.model.Project;
import com.example.demo.model.UserFeatures;
import com.example.demo.model.UserProjectRelationship;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.object.UserProjectRelationshipObject;
import com.example.demo.object.UserProjectRequest;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserProjectRelationshipRepository;
import com.example.demo.repository.UserTimesheetRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class ProjectService {
	
	@Autowired
	private EmailService emailService;
	
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserProjectRelationshipRepository userProjectRelationshipRepository;
	@Autowired
	private UserTimesheetRepository userTimesheetRepository;
	
	
	public ListResponse getProject(String user, Boolean isAdmin, AllUserRequest request) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<Project> projectEntityList = null;
		request.setSortByCol(request.getSortByCol() == null ? new String[] {"createdDate"} : request.getSortByCol());
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
	
	public ResponseEntity<?> projectDetails(String email, int id) throws InterruptedException, ExecutionException {
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
	
	public ResponseEntity<?> getProjectUserDetails(String user, String email, int projectId) throws InterruptedException, ExecutionException {
		if (projectId > 0 && !email.isEmpty()) {
			UserProjectRelationship users = userProjectRelationshipRepository.findByEmailAndProject(email, projectId);
			if(!users.getEmail().isEmpty()) {
				return new ResponseEntity<> (users, HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable find Users", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> updateProject(String user, UserProjectRequest request) throws InterruptedException, ExecutionException {
		if (request.getId() > 0) {
			int res = projectRepository.updateProjectById(request.getProjectName(), request.getProjectLocation(), request.getProjectDescription(), request.getModifiedDate(), request.getId());
			if(res > 0) {
				List<UserProjectRelationship> users = userProjectRelationshipRepository.findAllByProjectId(request.getId());
				if(users.size() > 0) {
					users.forEach(sm -> {
						emailService.sendSimpleMailMessage("Hi",sm.getEmail(), "Your details updated in project. Please login to account and verify.");
					});
				}
				GeneralUtilities.response("000", "Project updated successfully", HttpStatus.OK);
			}
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	public ResponseEntity<?> addUsersToProject(String user, List<UserProjectRelationship> req) throws InterruptedException, ExecutionException {
		if (req.size() > 0) {
			 List<UserProjectRelationship> res = userProjectRelationshipRepository.saveAll(req);
			 if (res.size() > 0) {
				 res.forEach(email -> {
					 emailService.sendSimpleMailMessage("Hi",email.getEmail(), "You are added to the project");
				 });
				 return GeneralUtilities.response("000", "User added to project successfully", HttpStatus.OK);
			 }
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> removeUsersFromProject(String user, List<UserProjectRelationship> req) throws InterruptedException, ExecutionException {
		if (req.size() > 0) {
			req.forEach(users -> {
				int res = userProjectRelationshipRepository.deleteByEmailAndProjectId(users.getProjectId(), users.getEmail());
				if (res > 0) {
					emailService.sendSimpleMailMessage("Hi",users.getEmail(), "You are removed to the project");
				}
			});
			return GeneralUtilities.response("000", "User removed from project successfully", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> updateUserInProject(String user, UserProjectRelationshipObject req) throws InterruptedException, ExecutionException {
		if (!req.getEmail().isEmpty() && req.getProjectId() != 0) {
			UserProjectRelationship newObj = new UserProjectRelationship();
			newObj.setProjectId(req.getProjectId());
			newObj.setEmail(req.getEmail());
			newObj.setEmployerPercentage(req.getEmployerPercentage());
			newObj.setId(req.getId());
			newObj.setIsAdmin(req.getIsAdmin());
			newObj.setPayRate(req.getPayRate());
			try {
				newObj.setProjectStartDate(date.parse(req.getProjectStartDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				newObj.setProjectEndDate(date.parse(req.getProjectEndDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int res = userProjectRelationshipRepository.updateByEmailAndProjectId(newObj.getEmail(), newObj.getProjectId(), newObj.getIsAdmin(), newObj.getProjectStartDate(), newObj.getProjectEndDate(), newObj.getPayRate(), newObj.getEmployerPercentage());
			
			if(res > 0) {

				emailService.sendHtmlEmail("Hi",newObj.getEmail(), "Your details updated in project. Please login to account and verify.", "");
				return GeneralUtilities.response("000", "User updated successfully.", HttpStatus.OK);
			}
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public Boolean checkUsersInProject(String user, String email, int projectId) throws InterruptedException, ExecutionException {
		if (!email.isEmpty() && projectId > 0) {
			UserProjectRelationship res = userProjectRelationshipRepository.findByEmailAndProject(email, projectId);
			if (res != null) {
				return true;
			}
			return false;
		}
		return false;
	}
};