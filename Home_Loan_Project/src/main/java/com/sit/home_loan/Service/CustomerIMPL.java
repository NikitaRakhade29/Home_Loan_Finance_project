package com.sit.home_loan.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.home_loan.DTO.CustomerProfileDTO;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanAppliaction;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.CustomerRepo;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Repository.UserRepo;

@Service
public class CustomerIMPL implements CustomerI {

	@Autowired
	UserRepo ur;

	@Autowired
	CustomerRepo cr;

	@Autowired
	LoanApplicationRepo lr;
	
	@Override
    public CustomerProfileDTO getProfileByEmail(String email) {
        Customers customer = cr.findByUserEmail(email)
            .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));

        CustomerProfileDTO dto = new CustomerProfileDTO();
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone_no(customer.getPhone_no());
        dto.setAddress(customer.getAddress());
        dto.setEmployment_type(customer.getEmployment_type());
        dto.setAadhaar_card(customer.getAadhaar_card());
        dto.setPan_card(customer.getPan_card());
        dto.setAccount_number(customer.getAccount_number());
        dto.setIfsc_code(customer.getIfsc_code());
        dto.setMontly_income(customer.getMontly_income());
        dto.setCity(customer.getCity());
        dto.setState(customer.getState());
        dto.setPincode(customer.getPincode());
        dto.setCibil(customer.getCibil());

        return dto;
    }

	@Override
	public Customers applyloan(LoanApplicationDTO loanDTO, Long customerId) {
		Customers customer = cr.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

		// Populate shared customer data from loanDTO
		customer.setAadhaar_card(loanDTO.getAadhar());
		customer.setPan_card(loanDTO.getPan_no());
		customer.setEmployment_type(loanDTO.getEmployment_type());
		customer.setMontly_income(loanDTO.getMonthly_income());
		customer.setAccount_number(loanDTO.getAccount_no());
		customer.setIfsc_code(loanDTO.getIfsc_code());

		// Generate random CIBIL score between 650 and 850
		int cibilScore = (int) (650 + Math.random() * 200);
		customer.setCibil(cibilScore);

		cr.save(customer);

		LoanAppliaction loan = new LoanAppliaction();
		loan.setLoan_amount(loanDTO.getLoan_amount());
		loan.setLoan_tenure(loanDTO.getLoan_tenure());
		loan.setLoan_purpose(loanDTO.getLoan_purpose());
		loan.setPan_no(loanDTO.getPan_no());
		loan.setAadhar(loanDTO.getAadhar());
		loan.setEmployment_type(loanDTO.getEmployment_type());
		loan.setEmployment_name(loanDTO.getEmployment_name());
		loan.setMonthly_income(loanDTO.getMonthly_income());
		loan.setAccount_no(loanDTO.getAccount_no());
		loan.setIfsc_code(loanDTO.getIfsc_code());
		loan.setAccount_holder_name(loanDTO.getAccount_holder_name());
		loan.setApplication_date(LocalDate.now());
		loan.setCustomer(customer);

		lr.save(loan);

		return customer;
	}

	@Override
	public Customers editProfile(Customers update_cust) {
		Customers add_data = cr.findById(update_cust.getCust_id())
				.orElseThrow(() -> new RuntimeException("customer id not found"));
		if (update_cust.getEmployment_type() != null) {
			add_data.setEmployment_type(update_cust.getEmployment_type());
		}
		if (update_cust.getAadhaar_card() != null) {
			add_data.setAadhaar_card(update_cust.getAadhaar_card());
		}
		if (update_cust.getPan_card() != null) {
			add_data.setPan_card(update_cust.getPan_card());
		}
		if (update_cust.getAccount_number() != null) {
			add_data.setAccount_number(update_cust.getAccount_number());
		}
		if (update_cust.getIfsc_code() != null) {
			add_data.setIfsc_code(update_cust.getIfsc_code());
		}
		if (update_cust.getMontly_income() != null) {
			add_data.setMontly_income(update_cust.getMontly_income());
		}
		if (update_cust.getCity() != null) {
			add_data.setCity(update_cust.getCity());
		}
		if (update_cust.getState() != null) {
			add_data.setState(update_cust.getState());
		}
		if (update_cust.getPincode() != null) {
			add_data.setPincode(update_cust.getPincode());
		}
		return cr.save(add_data);
	}
}
