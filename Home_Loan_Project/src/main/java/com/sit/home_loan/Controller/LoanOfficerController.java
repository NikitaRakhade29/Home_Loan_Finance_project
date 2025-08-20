package com.sit.home_loan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
import com.sit.home_loan.Service.LoanOfficerI;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/loan-officer")
@CrossOrigin("*")
public class LoanOfficerController {
	
	@Autowired
	LoanOfficerI lor;
	
	@GetMapping(path="pending-applications")
	public List<LoanApplicationDetailsDTO> getPendingApplications() {
		return lor.getAllPendingApplications();
	}
	
	@PostMapping(path="/review-cibil")
	public String reviewCibilScore(@RequestParam long applicationId, @RequestParam String officerEmail,
			@RequestParam boolean reject, @RequestParam(required = false) String reasonIfReject) {	
		return lor.reviewCibilDecision(applicationId,officerEmail,reject,reasonIfReject);
	}

}
