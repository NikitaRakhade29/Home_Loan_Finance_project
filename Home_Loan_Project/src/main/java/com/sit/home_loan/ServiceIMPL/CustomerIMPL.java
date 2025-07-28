package com.sit.home_loan.ServiceIMPL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
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

	        // Check for existing applications
	        List<LoanAppliaction> existing = lr.findByCustomerEmail(loanDTO.getEmail());
	        if (!existing.isEmpty()) {
	            return "You have already applied for a loan.";
	        }

	        // Update customer info
	        customer.setAadhaar_card(loanDTO.getAadhar());
	        customer.setPan_card(loanDTO.getPan_no());
	        customer.setEmployment_type(loanDTO.getEmployment_type());
	        customer.setMontly_income(loanDTO.getMonthly_income());
	        customer.setAccount_number(loanDTO.getAccount_no());
	        customer.setIfsc_code(loanDTO.getIfsc_code());

	        // Generate CIBIL
	        int cibilScore = (int) (650 + (Math.random() * 200));
	        customer.setCibil(cibilScore);
	        cr.save(customer);

	        // Populating the data loan application
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
	        return "An error occurred while applying for the loan.";
	    }
	}


	@Override
	public void editProfileByEmail(String email, Customers updateCust) {
	    Customers existingCustomer = cr.findByUserEmail(email).orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));

	    if (updateCust.getEmployment_type()!=null) {
	        existingCustomer.setEmployment_type(updateCust.getEmployment_type());
	    }
	    if (updateCust.getAadhaar_card()!=null) {
	        existingCustomer.setAadhaar_card(updateCust.getAadhaar_card());
	    }
	    if (updateCust.getPan_card()!=null) {
	        existingCustomer.setPan_card(updateCust.getPan_card());
	    }
	    if (updateCust.getAccount_number()!=null) {
	        existingCustomer.setAccount_number(updateCust.getAccount_number());
	    }
	    if (updateCust.getIfsc_code()!=null) {
	        existingCustomer.setIfsc_code(updateCust.getIfsc_code());
	    }
	    if (updateCust.getMontly_income()!=null) {
	        existingCustomer.setMontly_income(updateCust.getMontly_income());
	    }
	    if (updateCust.getCity()!=null) {
	        existingCustomer.setCity(updateCust.getCity());
	    }
	    if (updateCust.getState()!=null) {
	        existingCustomer.setState(updateCust.getState());
	    }
	    if (updateCust.getPincode()!=null) {
	        existingCustomer.setPincode(updateCust.getPincode());
	    }
	    cr.save(existingCustomer);
	}


	@Override
	public LoanApplicationDetailsDTO getLoanApplicationsByEmail(String email) {
	    List<LoanAppliaction> applications = lr.findByCustomerEmail(email);

	    if (applications.isEmpty()) {
	        throw new RuntimeException("No loan applications found for email: " + email);
	    }

	    // Get the latest loan application till last and avoid exception
	    LoanAppliaction loan = applications.get(applications.size() - 1);

	    LoanApplicationDetailsDTO dto = new LoanApplicationDetailsDTO();
	    dto.setEmail(loan.getCustomer().getUser().getEmail());
	    dto.setLoan_amount(loan.getLoan_amount());
	    dto.setLoan_tenure(loan.getLoan_tenure());
	    dto.setLoan_purpose(loan.getLoan_purpose());
	    dto.setPan_no(loan.getPan_no());
	    dto.setAadhar(loan.getAadhar());
	    dto.setEmployment_type(loan.getEmployment_type());
	    dto.setEmployment_name(loan.getEmployment_name());
	    dto.setMonthly_income(loan.getMonthly_income());
	    dto.setCibil(loan.getCibil());
	    dto.setAccount_no(loan.getAccount_no());
	    dto.setIfsc_code(loan.getIfsc_code());
	    dto.setAccount_holder_name(loan.getAccount_holder_name());
	    dto.setApplication_rejection_reason(loan.getApplication_rejection_reason());
	    dto.setApplication_date(loan.getApplication_date());
	    dto.setApplicationstatus(loan.getApplicationstatus());

	    return dto;
	}
	
	@Override
	public String deleteLoanApplicationByEmail(String email) {
	    List<LoanAppliaction> applications = lr.findByCustomerEmail(email);

	    if (applications == null || applications.isEmpty()) {
	        throw new RuntimeException("No loan application found for email: " + email);
	    }
	    lr.deleteAll(applications);

	    return "Loan application deleted successfully by " + email;
	}

}
