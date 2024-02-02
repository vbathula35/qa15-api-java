package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.PaymentProjectRelationship;
import com.example.demo.model.Payments;
import com.example.demo.model.UserProjectRelationship;
import com.example.demo.model.UserTimesheets;
import com.example.demo.model.Users;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.TimesheetRequest;
import com.example.demo.object.UsersList;
import com.example.demo.repository.PaymentProjectRelationshipRepository;
import com.example.demo.repository.PaymentsRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserProjectRelationshipRepository;
import com.example.demo.repository.UserSpecification;
import com.example.demo.repository.UserTimesheetRepository;
import com.example.demo.repository.UserTimesheetSpecification;
import com.example.demo.utils.GeneralUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TimeSheetService {
	@Autowired
	private UserTimesheetRepository userTimesheetRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Autowired
	private UserProjectRelationshipRepository userProjectRelationshipRepository;
	
	@Autowired
	private PaymentProjectRelationshipRepository paymentProjectRelationshipRepository;
	
	
	
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter dateFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
	
	
	public ListResponse getProjectTimesheets(Boolean isAdmin, String user, AllUserRequest request, int projectId) throws InterruptedException, ExecutionException{
		ListResponse finalRes = new ListResponse();
		Page<UserTimesheets> timesheetEntityList = null;
		request.setSortByCol((request.getSortByCol() == null) ? new String[] {"year", "month"} : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		if (request.getFilterBy() == null) {
			timesheetEntityList = isAdmin ? userTimesheetRepository.findAllTimeSheetsByProject(projectId, pageRequest) : userTimesheetRepository.findAllTimeSheetsByEmailAndProject(user, projectId, pageRequest);
		} else {
			switch (request.getFilterBy()) {
				case "email":
					if(isAdmin) {
						timesheetEntityList = userTimesheetRepository.findAllTimeSheetsByEmailAndProject(request.getFilterValue(), projectId, pageRequest);
					}
					break;
				case "month":
					timesheetEntityList = isAdmin ? userTimesheetRepository.findAllTimeSheetsByMonthAndProject(request.getFilterValue(), projectId, pageRequest) : userTimesheetRepository.findAllTimeSheetsByEmailAndProjectAndMonth(user, projectId, request.getFilterValue(), pageRequest);
					break;
				case "year":
					timesheetEntityList = isAdmin ? userTimesheetRepository.findAllTimeSheetsByYearAndProject(request.getFilterValue(), projectId, pageRequest) : userTimesheetRepository.findAllTimeSheetsByEmailAndProjectAndYear(user, projectId, request.getFilterValue(), pageRequest);
					break;
				default:
					timesheetEntityList = isAdmin ? userTimesheetRepository.findAllTimeSheetsByProject(projectId, pageRequest) : userTimesheetRepository.findAllTimeSheetsByEmailAndProject(user, projectId, pageRequest);
		            break;
			}
		}
		finalRes.setPageNumber(timesheetEntityList.getNumber());
		finalRes.setPageSize(timesheetEntityList.getSize());
		finalRes.setTotal(timesheetEntityList.getTotalElements());
		finalRes.setOffSet(timesheetEntityList.getPageable().getOffset());
		finalRes.setResults(timesheetEntityList.getContent());
		return finalRes;
	}


	
	public ResponseEntity<?> submitMonthlytimesheets(String user, int year, int month, int projectId, List<TimesheetRequest> timesheets) throws InterruptedException, ExecutionException, ParseException, JsonProcessingException {
		if (!user.isEmpty() && year > 0 && month > 0 && projectId > 0 && timesheets != null) {
			
			UserTimesheets find = userTimesheetRepository.findTimesheetByUserAndProject(user, projectId, year, month);
			
			if (find == null) {
				UserTimesheets obj = new UserTimesheets();
				obj.setEmail(user);
				obj.setMonth(month);
				obj.setYear(year);
				obj.setProjectId(projectId);
				ObjectMapper map = new ObjectMapper();
				obj.setTimesheet(map.writeValueAsString(timesheets));
				UserTimesheets res = userTimesheetRepository.saveAndFlush(obj);
				
				if (res != null) {
					Payments payment = new Payments();
					payment.setFederalTax(0);
					payment.setOtherPayment(0);
					payment.setOtherTax(0);
					payment.setStateTax(0);
					payment.setTakeHome(0);
					payment.setTax(0);
					int totalHrs = timesheets.stream().map(v -> v.getHours()).reduce(0, (a, b) -> a + b); 
							
					UserProjectRelationship usrPrjRltsp = userProjectRelationshipRepository.findByEmailAndProject(user, projectId);
					int employeePayrate = (100 - usrPrjRltsp.getEmployerPercentage());
					float employeePayrateDecimal = (float) employeePayrate / 100;
					int payRate =  (int) ((float) employeePayrateDecimal * usrPrjRltsp.getPayRate());
							
					int totalPayAmount = totalHrs * payRate;
					payment.setTotalAmount(totalPayAmount);
				
					Payments paymentRes = paymentsRepository.save(payment);
					if (paymentRes != null) {
						PaymentProjectRelationship paymentProjectRelationship = new PaymentProjectRelationship();
						paymentProjectRelationship.setEmail(user);
						paymentProjectRelationship.setMonth(month);
						paymentProjectRelationship.setPaymentId(paymentRes.getPaymentId());
						paymentProjectRelationship.setProjectId(projectId);
						paymentProjectRelationship.setYear(year);
						paymentProjectRelationshipRepository.save(paymentProjectRelationship);
						return GeneralUtilities.response("000", "Timesheet submitted successfully.", HttpStatus.OK);
					}
				}
			}
			return GeneralUtilities.response("003", "Timesheet already submitted for this month.", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	} 
	
	
	public ResponseEntity<?> timesheets(String user, int year, int month, int projectId) throws InterruptedException, ExecutionException, ParseException { 
		if (!user.isEmpty() && year > 0 && month > 0 && projectId > 0) {
			UserTimesheets res = userTimesheetRepository.findTimesheetByUserAndProject(user, projectId, year, month);
			return new ResponseEntity<> (res, HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	
	public ResponseEntity<?> updateTimesheets(String user, int year, int month, int projectId, List<TimesheetRequest> timesheets) throws JsonProcessingException {
		if (year > 0 && month > 0 && projectId > 0 && !user.isEmpty() && timesheets.size() > 0) {
			ObjectMapper map = new ObjectMapper();
			String timeSheet = map.writeValueAsString(timesheets);
			int res = userTimesheetRepository.updateTimesheet(user, projectId, month, year, timeSheet);
			if(res > 0) {
				return GeneralUtilities.response("000", "Timesheet updated successfully.", HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable to updated timesheet. Please try again.", HttpStatus.OK);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	

}

