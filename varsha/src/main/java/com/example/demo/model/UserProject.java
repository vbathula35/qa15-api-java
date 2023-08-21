package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "user_projects")
public class UserProject {
	@Id
	@Column(name = "id", nullable = false, length = 100, unique = true)
	@GenericGenerator(name= "uuid-gen", strategy = "uuid")
	@GeneratedValue(generator = "uuid-gen")
	@Nationalized
	private String id;
	
	@Column(name = "projectName", nullable = false, length = 50)
	private String projectName;
	
	@Column(name = "projectDescription", nullable = true, length = 100)
	private String projectDescription;
	
	@Column(name = "projectLocation", nullable = false, length = 50)
	@Nationalized
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
