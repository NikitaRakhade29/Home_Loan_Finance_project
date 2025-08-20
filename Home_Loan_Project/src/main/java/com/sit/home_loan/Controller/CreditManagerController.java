package com.sit.home_loan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.DTO.LoanWithDocumentsDTO;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Model.SanctionLetter;
import com.sit.home_loan.Service.CreditManagerI;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/CreditManager")
@CrossOrigin("*")
public class CreditManagerController {
	@Autowired
	CreditManagerI cmi;

	@GetMapping("loan-applications/submitted")
	public List<LoanApplication> getSumbittedApplications() {
		return cmi.getApplicationsWithDocumentsSumbmitted();
	}

	@GetMapping("/loan-with-documents/{loanAppId}")
	public LoanWithDocumentsDTO getLoanWithDocuments(@PathVariable Long loanAppId) {
		return cmi.getLoanWithDocuments(loanAppId);
	}

	@PutMapping("/documents/verify/{id}")
	public String updateDocumentStatus(@PathVariable Long id, @RequestParam String status) {
		cmi.updateVerificationStatus(id, status);
		return "Document status is updated to: " + status.toUpperCase();
	}
	
	@PutMapping("/evaluate/{loanAppId}")
	public String evaluateLoan(@PathVariable Long loanAppId) {
		cmi.evaluateLoanApplication(loanAppId);
		return "Loan Application Evaluated Sucessfully";
	}
	
	@PostMapping("/sanction/{loanAppId}")
	public SanctionLetter generateSanction(@PathVariable Long loanAppId, @RequestBody SanctionLetter request){
		return cmi.generateSanctionLetter(loanAppId,request);
	}

}
