package com.sit.home_loan.Service;

//import com.sit.home_loan.DTO.CustomerProfileDTO;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.Model.Customers;

public interface CustomerI {
	Customers applyloan(LoanApplicationDTO loanDTO, Long customerId);
	Customers editProfile(Customers update_cust);
	Customers getProfileByEmail(String email);
}
