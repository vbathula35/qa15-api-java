package com.example.demo.repository;

import java.util.List;

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
	
	
	@Query ("SELECT ut FROM Timesheet ut WHERE ut.email = :email AND ut.week = :week AND ut.month = :month AND ut.year = :year")
	public List<Timesheet> findTimesheetsByWeek(String email, Integer week, Integer month, Integer year);
	

	
}

