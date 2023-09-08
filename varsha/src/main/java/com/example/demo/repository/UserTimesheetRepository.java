package com.example.demo.repository;

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
import com.example.demo.model.Timesheet;




public interface UserTimesheetRepository extends JpaRepository<Timesheet, Integer>, JpaSpecificationExecutor<Timesheet> {	
	@Transactional
	@Modifying
	@Query ("UPDATE UserTask SET status = :status where id = :id")
	void updateStatusById(Integer id, String status);
	
	@Query (value = "SELECT ts FROM Timesheet ts WHERE ts.date BETWEEN str_to_date(:startDate, '%Y-%m-%d') AND str_to_date(:endDate, '%Y-%m-%d') AND ts.email = :email")
	public List<Timesheet> findTimesheetsByWeek(String email, String startDate, String endDate);
	
	@Query ("SELECT ts FROM Timesheet ts WHERE ts.projectId = :projectId AND ts.email = :email")
	public List<Timesheet> findTimesheetsByUserAndProject(String email, String projectId);
	
}

