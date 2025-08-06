package com.sit.home_loan.Service;

import java.util.List;

import com.sit.home_loan.Model.LoanApplication;

public interface DisbursementI {
	List<LoanApplication> getSanctionedApplications();
	String disbursementLoan(Long loanAppId, Double amount);

}
