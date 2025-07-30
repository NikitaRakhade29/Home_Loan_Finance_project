package com.sit.home_loan.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//import com.sit.home_loan.DTO.CustomerProfileDTO;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanApplication;

public interface CustomerI {

	Customers getProfile(String email);
	String applyForloan(LoanApplicationDTO loanDTO);
	List<LoanApplicationDetailsDTO> getLoanApplications(String email);
	String deleteLoanApplication(String email);
	void editProfileByEmail(String email, Customers updateCust);


}
