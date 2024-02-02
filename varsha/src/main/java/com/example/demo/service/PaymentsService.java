package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.example.demo.model.Project;
import com.example.demo.object.AllUserRequest;
import com.example.demo.object.ListResponse;
import com.example.demo.object.PaymentObject;
import com.example.demo.repository.PaymentProjectRelationshipRepository;
import com.example.demo.repository.PaymentsRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserProjectRelationshipRepository;
import com.example.demo.utils.GeneralUtilities;

@Service
public class PaymentsService {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PaymentsRepository  paymentsRepository;
	@Autowired
	private PaymentProjectRelationshipRepository paymentProjectRelationshipRepository;
	
	
	public ListResponse getPaymentsByProject(String user, AllUserRequest request, int projectId) throws InterruptedException, ExecutionException {
		ListResponse finalRes = new ListResponse();
		Page<?> paymentEntityList = null;
		request.setSortByCol(request.getSortByCol() == null ? new String[] {"year", "month"} : request.getSortByCol());
		request.setSortByDirection((request.getSortByDirection() == null || request.getSortByDirection().isEmpty()) ? "DESC" : request.getSortByDirection());
		PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(GeneralUtilities.sortDirection(request.getSortByDirection()), request.getSortByCol()));
		
		paymentEntityList = paymentProjectRelationshipRepository.findPaymentsByProjectId(pageRequest, projectId);

		finalRes.setPageNumber(paymentEntityList.getNumber());
		finalRes.setPageSize(paymentEntityList.getSize());
		finalRes.setTotal(paymentEntityList.getTotalElements());
		finalRes.setOffSet(paymentEntityList.getPageable().getOffset());
		finalRes.setResults(paymentEntityList.getContent());
		return finalRes;
	}
	
	public ResponseEntity<?> paymentsDetils(String user, int paymentId) throws InterruptedException, ExecutionException {
		if (paymentId != 0) {
			Optional<Payments> payment = paymentsRepository.findById(paymentId);
			if (!payment.isEmpty()) {
				return new ResponseEntity<> (payment, HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable find Project", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<?> updatePayments(String user, Payments r) throws InterruptedException, ExecutionException {
		if (r.getPaymentId() != 0) {
			int res = paymentsRepository.updatePaymentByPaymentId(r.getTotalAmount(), r.getTax(), r.getTakeHome(), r.getStateTax(), r.getOtherTax(), r.getOtherPayment(), r.getFederalTax(), r.getComments(), r.getPaymentId());
			if (res > 0) {
				return GeneralUtilities.response("000", "Payment updated successfully", HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable to update payment, Please try after sometime.", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<?> createPayment(String user,  PaymentObject r) throws InterruptedException, ExecutionException {
		if (!r.getEmail().isEmpty()&& r.getProjectId() > 0) {
			Payments payment = new Payments();
			
			payment.setComments(r.getComments());
			payment.setFederalTax(r.getFederalTax());
			payment.setOtherPayment(r.getOtherPayment());
			payment.setOtherTax(r.getOtherTax());
			payment.setStateTax(r.getStateTax());
			payment.setTakeHome(r.getTakeHome());
			payment.setTax(r.getTax());
			payment.setTotalAmount(r.getTotalAmount());
			
			Payments res = paymentsRepository.save(payment);
			if (res != null) {
				PaymentProjectRelationship paymentProjectRelationship = new PaymentProjectRelationship();
				paymentProjectRelationship.setEmail(r.getEmail());
				paymentProjectRelationship.setMonth(r.getMonth());
				paymentProjectRelationship.setPaymentId(res.getPaymentId());
				paymentProjectRelationship.setProjectId(r.getProjectId());
				paymentProjectRelationship.setYear(r.getYear());
				paymentProjectRelationshipRepository.save(paymentProjectRelationship);
				emailService.sendSimpleMailMessage("Hi", r.getEmail(), "New payment created for you. Please login to your account and verify.");
				return GeneralUtilities.response("000", "Payment updated successfully", HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable to create payment, Please try after sometime.", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> deletePayment(String user, int paymentId) throws InterruptedException, ExecutionException {
		if (paymentId > 0) {
			int res = paymentsRepository.deletePaymentByPaymentId(paymentId);
			if (res > 0) {
				paymentProjectRelationshipRepository.deletePaymentByPaymentId(paymentId);
				return GeneralUtilities.response("000", "Payment deleted successfully", HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable to delete payment, Please try after sometime.", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> paymentDetailsByProject(String user, PaymentObject request) throws InterruptedException, ExecutionException {
		if (!request.getEmail().isEmpty() && request.getProjectId() > 0 && request.getYear() > 0 && request.getMonth() > 0) {
			
			Payments payment = paymentsRepository.findByUserAndProjectId(request.getProjectId(), request.getMonth(), request.getYear(), request.getEmail());
			if (payment.getPaymentId() > 0) {
				return new ResponseEntity<> (payment, HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Unable to find payment.", HttpStatus.CONFLICT);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> paymentDetailsByUserAndProject (String email, int projectId) throws InterruptedException, ExecutionException {
		if (!email.isEmpty() && projectId > 0) {
			List<PaymentProjectRelationship> payReltn =  paymentProjectRelationshipRepository.findPaymentsByProjectIdAndEmailWithOutPagination(projectId, email);
			List<Object> newPaymentArray = new ArrayList<>();
			if (payReltn.size() > 0) {
				payReltn.forEach(paymentRltn -> {
					PaymentObject newPaymentObject = new PaymentObject();
					
					Optional<Payments> payment = paymentsRepository.findById(paymentRltn.getPaymentId());
					
					newPaymentObject.setEmail(paymentRltn.getEmail());
					newPaymentObject.setYear(paymentRltn.getYear());
					newPaymentObject.setMonth(paymentRltn.getMonth());
					newPaymentObject.setOtherPayment(payment.get().getOtherPayment());
					newPaymentObject.setFederalTax(payment.get().getFederalTax());
					newPaymentObject.setStateTax(payment.get().getStateTax());
					newPaymentObject.setTakeHome(payment.get().getTakeHome());
					newPaymentObject.setTax(payment.get().getTax());
					newPaymentObject.setTotalAmount(payment.get().getTotalAmount());
					newPaymentObject.setOtherPayment(payment.get().getOtherPayment());
					newPaymentObject.setOtherTax(payment.get().getOtherTax());
					newPaymentArray.add(newPaymentObject);
				});
				return new ResponseEntity<> (newPaymentArray, HttpStatus.OK);
			}
			return GeneralUtilities.response("003", "Payments Not Found", HttpStatus.BAD_REQUEST);
		}
		return GeneralUtilities.response("002", "Bad Request", HttpStatus.BAD_REQUEST);
	}
	
}
