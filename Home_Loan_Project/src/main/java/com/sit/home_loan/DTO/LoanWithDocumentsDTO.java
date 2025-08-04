package com.sit.home_loan.DTO;

import java.util.List;

import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.LoanApplication;

import lombok.Data;

@Data
public class LoanWithDocumentsDTO {
	private LoanApplication loan;
	private List<CustomerDocuments> documents;
}
