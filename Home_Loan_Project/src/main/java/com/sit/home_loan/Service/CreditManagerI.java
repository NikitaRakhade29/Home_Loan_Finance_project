package com.sit.home_loan.Service;

import java.util.List;


import com.sit.home_loan.DTO.LoanWithDocumentsDTO;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.LoanApplication;

public interface CreditManagerI {
	List<LoanApplication> getApplicationsWithDocumentsSumbmitted();
	LoanWithDocumentsDTO getLoanWithDocuments(long loanAppId);
}
