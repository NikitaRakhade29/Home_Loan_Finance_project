package com.sit.home_loan.ServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.sit.home_loan.DTO.LoanWithDocumentsDTO;
import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Enum.VerificationStatus;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Repository.CreditManagerRepo;
import com.sit.home_loan.Repository.DocumentRepo;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Service.CreditManagerI;

@Service
public class CreditManagerIMPL implements CreditManagerI {

	@Autowired
	LoanApplicationRepo lr;
	
	@Autowired
	DocumentRepo dr;
	
	@Autowired
	CreditManagerRepo cmr;

	@Override
	public List<LoanApplication> getApplicationsWithDocumentsSumbmitted() {
		return lr.findByApplicationStatus(ApplicationStatus.DOCUMENT_UPLOADED);
	}

	@Override
	public LoanWithDocumentsDTO getLoanWithDocuments(long loanAppId) {
		LoanWithDocumentsDTO dto = new LoanWithDocumentsDTO();
		
		LoanApplication loan=lr.findById(loanAppId).orElse(null);
		if(loan == null) {
			return null;
		}
		
		Customers cust = loan.getCustomer();
		List<CustomerDocuments> docs = new ArrayList<>();
		
		if(cust != null) {
			docs = dr.findByCustomer_CustId(cust.getCustId());
		}
		
		dto.setLoan(loan);
		dto.setDocuments(docs);
		return dto;
	}

	

}
