package com.example.demo.object;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserProjectRequest {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String projectName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String projectDescription;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String projectLocation;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate modifiedDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectLocation() {
		return projectLocation;
	}
	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
