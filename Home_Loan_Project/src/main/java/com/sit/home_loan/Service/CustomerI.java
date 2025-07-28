package com.sit.home_loan.Service;

//import com.sit.home_loan.DTO.CustomerProfileDTO;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanAppliaction;

public interface CustomerI {

	Customers getProfileByEmail(String email);
	String applyForloan(LoanApplicationDTO loanDTO);
	LoanApplicationDetailsDTO getLoanApplicationsByEmail(String email);
	String deleteLoanApplicationByEmail(String email);
	void editProfileByEmail(String email, Customers updateCust);

}
