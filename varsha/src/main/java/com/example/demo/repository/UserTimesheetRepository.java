package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Project;
import com.example.demo.model.UserTimesheets;




public interface UserTimesheetRepository extends JpaRepository<UserTimesheets, Integer>, JpaSpecificationExecutor<UserTimesheets> {	
	
	@Transactional
	@Modifying
	@Query ("DELETE FROM UserTimesheets t WHERE t.projectId = :id")
	void deleteTimesheetsByProjectId(int id);
	
	
	@Query ("SELECT t FROM UserTimesheets t WHERE t.projectId = :projectId AND t.email = :email AND t.year = :year AND t.month = :month")
	UserTimesheets findTimesheetByUserAndProject(String email, int projectId, int year, int month);

	UserTimesheets saveAndFlush(UserTimesheets obj);
	
	
	@Query("SELECT t FROM UserTimesheets t WHERE t.projectId = :projectId")
	public Page<UserTimesheets> findAllTimeSheetsByProject(int projectId, Pageable pageable);
	
	@Query("SELECT t FROM UserTimesheets t WHERE t.projectId = :projectId AND t.month = :month")
	public Page<UserTimesheets> findAllTimeSheetsByMonthAndProject(String month, int projectId, Pageable pageable);
	
	@Query("SELECT t FROM UserTimesheets t WHERE t.projectId = :projectId AND t.year = :year")
	public Page<UserTimesheets> findAllTimeSheetsByYearAndProject(String year, int projectId, Pageable pageable);
	
	
	
	@Query("SELECT t FROM UserTimesheets t WHERE t.email = :email AND t.projectId = :projectId")
	public Page<UserTimesheets> findAllTimeSheetsByEmailAndProject(String email, int projectId, Pageable pageable);
	
	@Query("SELECT t FROM UserTimesheets t WHERE t.email = :email AND t.projectId = :projectId AND t.month = :month")
	public Page<UserTimesheets> findAllTimeSheetsByEmailAndProjectAndMonth(String email, int projectId, String month, Pageable pageable);
	
	@Query("SELECT t FROM UserTimesheets t WHERE t.email = :email AND t.projectId = :projectId AND t.year = :year")
	public Page<UserTimesheets> findAllTimeSheetsByEmailAndProjectAndYear(String email, int projectId, String year, Pageable pageable);
	
	
	
	@Transactional
	@Modifying
	@Query ("UPDATE UserTimesheets t SET t.timesheet = :timesheet WHERE t.email = :email AND t.projectId = :projectId AND t.month = :month AND t.year = :year")
	int updateTimesheet(String email, int projectId, int month, int year, String timesheet);

	
}

