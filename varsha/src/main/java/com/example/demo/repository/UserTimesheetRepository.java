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
import com.example.demo.model.Timesheet;




public interface UserTimesheetRepository extends JpaRepository<Timesheet, Integer>, JpaSpecificationExecutor<Timesheet> {	
	@Transactional
	@Modifying
	@Query ("UPDATE UserTask SET status = :status where id = :id")
	void updateStatusById(Integer id, String status);
	
//	@Query (value = "SELECT ts FROM Timesheet ts WHERE ts.date >= :startDate AND ts.date <= :endDate AND ts.email = :email AND ts.projectId = :projectId")
	@Query (value = "SELECT ts FROM Timesheet ts WHERE ts.date BETWEEN :startDate AND :endDate AND ts.email = :email AND ts.projectId = :projectId")
	public List<Timesheet> findTimesheetsByWeek(String email, LocalDate startDate, LocalDate endDate, int projectId);
	
	@Query ("SELECT ts FROM Timesheet ts WHERE ts.projectId = :projectId AND ts.email = :email")
	public List<Timesheet> findTimesheetsByUserAndProject(String email, int projectId);
	
	@Transactional
	@Modifying
	@Query ("DELETE FROM Timesheet t WHERE t.projectId = :id")
	void deleteTimesheetsByProjectId(int id);
	
}

