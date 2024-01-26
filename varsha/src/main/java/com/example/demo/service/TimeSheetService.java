package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AppConstant;
import com.example.demo.model.Timesheet;
import com.example.demo.model.UserAuth;
import com.example.demo.model.UserFeatures;
import com.example.demo.model.UserPermissions;
import com.example.demo.model.Users;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.Response;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.object.User;
import com.example.demo.repository.UserTaskSpecification;
import com.example.demo.repository.UserTimesheetRepository;
import com.example.demo.repository.UserTimesheetSpecification;
import com.example.demo.utils.GeneralUtilities;

@Service
public class TimeSheetService {
	@Autowired
	private UserTimesheetRepository userTimesheetRepository;
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter dateFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd");	

	
	
	public ListResponse getAllTimesheets(String user, Boolean isAdmin, AllUserRequest request) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<Timesheet> timeSheetEntityList = null;
		request.setSortByCol((request.getSortByCol() == null || request.getSortByCol().isEmpty()) ? "email" : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		
		if (isAdmin) {
			timeSheetEntityList = userTimesheetRepository.findAll(pageRequest);
		} else {
			timeSheetEntityList = userTimesheetRepository.findAll(UserTimesheetSpecification.findByEmail(user), pageRequest);
		}
		
		finalRes.setPageNumber(timeSheetEntityList.getNumber());
		finalRes.setPageSize(timeSheetEntityList.getSize());
		finalRes.setTotal(timeSheetEntityList.getTotalElements());
		finalRes.setOffSet(timeSheetEntityList.getPageable().getOffset());
		finalRes.setResults(timeSheetEntityList.getContent());
		return finalRes;
	}
	
	
	
	
	
	
	public ResponseEntity<Response> saveCurrentTimesheet(String email, List<TimesheetRequest> timesheetRequest) throws InterruptedException, ExecutionException {
		if (!timesheetRequest.isEmpty() || timesheetRequest.size() > 0) {
			List<Timesheet> timeSheetEntitys = new ArrayList<Timesheet>();
			timesheetRequest.forEach(timeSheet -> {
				Timesheet timeSheetEntity = new Timesheet();
				timeSheetEntity.setEmail(email);
				timeSheetEntity.setDate(LocalDate.parse(timeSheet.getDate(), dateFormatter));
				
//				try {
//					timeSheetEntity.setDate(date.parse(timeSheet.getDate()));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				timeSheetEntity.setDay(timeSheet.getDay());
				timeSheetEntity.setHours(timeSheet.getHours());
				timeSheetEntity.setIsHolyday(timeSheet.getIsHolyday());
				timeSheetEntity.setStatus("Submitted");
				timeSheetEntity.setUpdatedBy(email);
				timeSheetEntity.setWeek(timeSheet.getWeek());
				timeSheetEntity.setMonth(timeSheet.getMonth());
				timeSheetEntity.setYear(timeSheet.getYear());
				timeSheetEntity.setProjectId(timeSheet.getProjectId());
				if (timeSheet.getNotes() != null && !timeSheet.getNotes().isEmpty()) {
					timeSheetEntity.setNotes(timeSheet.getNotes());
					timeSheetEntity.setNotesUpdatedBy(email);
				}
				timeSheetEntitys.add(timeSheetEntity);
			});
			userTimesheetRepository.saveAll(timeSheetEntitys);
			return GeneralUtilities.response("000", "User created successfully. Please confirm with pin to activate account.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> getWeekTimesheets(String user, TimesheetRequest request) throws InterruptedException, ExecutionException, ParseException {
		if (!request.getStartDate().isEmpty() && !request.getEndDate().isEmpty()) {
			LocalDate sd = LocalDate.parse(request.getStartDate(), dateFormatter);
			LocalDate ed =  LocalDate.parse(request.getEndDate(), dateFormatter);
			LocalDateTime startDate = sd.atStartOfDay();
			LocalDateTime endDate = ed.atStartOfDay();
			return new ResponseEntity<>(userTimesheetRepository.findTimesheetsByWeek(user,sd ,ed , request.getProjectId()),  HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	

}

