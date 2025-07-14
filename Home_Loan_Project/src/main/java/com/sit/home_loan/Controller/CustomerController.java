package com.sit.home_loan.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.DTO.CustomerProfileDTO;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanAppliaction;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.CustomerRepo;
import com.sit.home_loan.Service.CustomerI;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerI ci;
	
	@GetMapping("/profile")
    public CustomerProfileDTO getMyProfile(@RequestParam String email) {
        return ci.getProfileByEmail(email);
    }

    @PostMapping(path="/apply-loan")
    public String applyLoan(@RequestBody LoanApplicationDTO loanDTO, @RequestParam Long customerId) {
        ci.applyloan(loanDTO, customerId);
        return "Loan application submitted successfully. ";
    }
    
    @PostMapping(path="/edit")
    public String editProfile(@RequestBody Customers update_cust) {
    	System.out.println(update_cust);
        ci.editProfile(update_cust);
        return "Profile edit Successfully..";
    }
    
//	@GetMapping(path="my_appliaction")
//	public String getMethodName(@RequestParam String param) {
//		return new String();
//	}
//	

}
