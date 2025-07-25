package com.sit.home_loan.ServiceIMPL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanAppliaction;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.CustomerRepo;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Repository.UserRepo;
import com.sit.home_loan.Service.CustomerI;

@Service
public class CustomerIMPL implements CustomerI {

	@Autowired
	UserRepo ur;

	@Autowired
	CustomerRepo cr;

	@Autowired
	LoanApplicationRepo lr;

	@Override
	public Customers getProfileByEmail(String email) {

		Optional<Customers> customer = cr.findByUserEmail(email);
		if (customer.isPresent()) {
			return customer.get();
		}else{
			throw new RuntimeException("Customer not found with this email" +email);
		}
	}
	
	@Override
	public String applyForloan(LoanApplicationDTO loanDTO) {
	    try {
	        Optional<Customers> customerOpt = cr.findByUserEmail(loanDTO.getEmail());

	        if (!customerOpt.isPresent()) {
	            return "Customer not found.";
	        }

	        Customers customer = customerOpt.get();

	        // Check if customer has already applied
	        List<LoanAppliaction> existingApplications = lr.findByCustomerEmail(loanDTO.getEmail());
	        if (!existingApplications.isEmpty()) {
	            return "You have already applied for a loan.";
	        }

	        // Update customer info
	        customer.setAadhaar_card(loanDTO.getAadhar());
	        customer.setPan_card(loanDTO.getPan_no());
	        customer.setEmployment_type(loanDTO.getEmployment_type());
	        customer.setMontly_income(loanDTO.getMonthly_income());
	        customer.setAccount_number(loanDTO.getAccount_no());
	        customer.setIfsc_code(loanDTO.getIfsc_code());

	        // Generate CIBIL score between 650 and 850
	        int cibilScore = (int) (650 + (Math.random() * 200));
	        customer.setCibil(cibilScore);
	        cr.save(customer);

	        // Prepare loan application
	        LoanAppliaction loan = new LoanAppliaction();
	        loan.setLoan_amount(loanDTO.getLoan_amount());
	        loan.setLoan_tenure(loanDTO.getLoan_tenure());
	        loan.setLoan_purpose(loanDTO.getLoan_purpose());
	        loan.setPan_no(loanDTO.getPan_no());
	        loan.setAadhar(loanDTO.getAadhar());
	        loan.setEmployment_type(loanDTO.getEmployment_type());
	        loan.setEmployment_name(loanDTO.getEmployment_name());
	        loan.setMonthly_income(loanDTO.getMonthly_income());
	        loan.setCibil(cibilScore);
	        loan.setAccount_no(loanDTO.getAccount_no());
	        loan.setIfsc_code(loanDTO.getIfsc_code());
	        loan.setAccount_holder_name(loanDTO.getAccount_holder_name());
	        loan.setApplication_date(LocalDate.now());
	        loan.setCustomer(customer);
	        loan.setApplicationstatus(ApplicationStatus.Pending);

	        lr.save(loan);

	        return "Loan application submitted successfully. CIBIL Score: " + cibilScore;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred while applying for the loan. Please try again later.";
	    }
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
