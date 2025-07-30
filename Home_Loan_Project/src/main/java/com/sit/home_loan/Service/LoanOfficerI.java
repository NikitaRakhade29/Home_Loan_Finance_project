package com.sit.home_loan.Service;

import java.util.List;

import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;

public interface LoanOfficerI {
	List<LoanApplicationDetailsDTO> getAllPendingApplications();
	String reviewCibilDecision(Long applicationId,String officerEmail,Boolean reject,String reasonIfReject);

}
