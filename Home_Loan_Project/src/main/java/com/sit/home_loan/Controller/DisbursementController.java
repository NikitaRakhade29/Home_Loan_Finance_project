package com.sit.home_loan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.Model.Disbursement;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Service.DisbursementI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/disbursement")
public class DisbursementController {

	@Autowired
	private DisbursementI di;

	@GetMapping("/sanctioned")
	public List<LoanApplication> getSanctionedLoans() {
		return di.getSanctionedApplications();
	}

	@PostMapping("/disbursementLoan/{LoanAppId}")
	public String disbursementLoan(@PathVariable("LoanAppId") long loanAppId, @RequestBody Disbursement disbursement) {
		return di.disbursementLoan(loanAppId, disbursement.getDisbursementAmount());
	}

}
