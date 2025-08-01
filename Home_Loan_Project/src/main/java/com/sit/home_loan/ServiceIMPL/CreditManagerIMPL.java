package com.sit.home_loan.ServiceIMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sit.home_loan.DTO.DocumentsDTO;
import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Enum.VerficationStatus;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Repository.DocumentsRepo;
import com.sit.home_loan.Service.CreditManagerI;

@Service
public class CreditManagerIMPL implements CreditManagerI {

	@Autowired
	DocumentsRepo dr;

	@Override
	public List<DocumentsDTO> getDocumentsByCustomerId(Long customerId) {
		List<CustomerDocuments> documents = dr.findByCustomer_CustId(customerId);
		List<DocumentsDTO> dtoList = new ArrayList<>();
		for (CustomerDocuments doc : documents) {
			DocumentsDTO dto = new DocumentsDTO();
			dto.setId(doc.getId());
			dto.setFileUrl(doc.getFileUrl());
			dto.setUploadDate(doc.getUploadDate());
			dto.setFilename(doc.getFilename());
			dto.setDocumentType(doc.getDocumentType());
			dto.setVerficationStatus(doc.getVerficationStatus());

			dtoList.add(dto);
		}
		return dtoList;
	}

}
