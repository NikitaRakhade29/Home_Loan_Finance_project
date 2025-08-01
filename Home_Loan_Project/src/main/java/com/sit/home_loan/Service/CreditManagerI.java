package com.sit.home_loan.Service;

import java.util.List;

import com.sit.home_loan.DTO.DocumentsDTO;
import com.sit.home_loan.Model.CustomerDocuments;

public interface CreditManagerI {
	List<DocumentsDTO> getDocumentsByCustomerId(Long customerId);
}
