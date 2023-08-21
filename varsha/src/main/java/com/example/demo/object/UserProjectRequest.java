package com.example.demo.object;
import com.fasterxml.jackson.annotation.JsonInclude;

public class UserProjectRequest {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;
	private String projectName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String projectDescription;
	private String projectLocation;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

}
