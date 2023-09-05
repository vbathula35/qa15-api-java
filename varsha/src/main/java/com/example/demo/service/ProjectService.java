package com.example.demo.service;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Project;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.ProjectSpecification;
import com.example.demo.repository.UserTaskSpecification;
import com.example.demo.utils.GeneralUtilities;

@Service
public class ProjectService {
	private ProjectRepository projectRepository;
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
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
			projectEntityList = projectRepository.findAll(ProjectSpecification.findByCreatedBy(user), pageRequest);
		}
		
		finalRes.setPageNumber(projectEntityList.getNumber());
		finalRes.setPageSize(projectEntityList.getSize());
		finalRes.setTotal(projectEntityList.getTotalElements());
		finalRes.setOffSet(projectEntityList.getPageable().getOffset());
		finalRes.setResults(projectEntityList.getContent());
		return finalRes;
	}
	
}
