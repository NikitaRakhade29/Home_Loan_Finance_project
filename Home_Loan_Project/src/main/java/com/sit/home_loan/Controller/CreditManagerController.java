package com.sit.home_loan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.DTO.LoanWithDocumentsDTO;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Service.CreditManagerI;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/CreditManager")
public class CreditManagerController {
	@Autowired
	CreditManagerI cmi;

	@GetMapping("loan-applications/submitted")
	public List<LoanApplication> getSumbittedApplications() {
		return cmi.getApplicationsWithDocumentsSumbmitted();
	}

	@GetMapping("loan-with-documents/{loanAppId}")
	public LoanWithDocumentsDTO getloanWithDocuments(@RequestParam Long loanAppId) {
		return cmi.getLoanWithDocuments(loanAppId);
	}

}
