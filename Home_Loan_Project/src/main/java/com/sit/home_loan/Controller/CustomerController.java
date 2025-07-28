package com.sit.home_loan.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanAppliaction;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.CustomerRepo;
import com.sit.home_loan.Service.CustomerI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public Customers getMyProfile(@RequestParam String email) {
        return ci.getProfileByEmail(email);
    }
	
	@PostMapping("/edit")
	public String editProfile(@RequestParam String email, @RequestBody Customers updateCust) {
	    ci.editProfileByEmail(email, updateCust);
	    return "Profile updated successfully.";
	}


    @PostMapping(path="/apply-loan")
    public String applyLoan(@RequestBody LoanApplicationDTO loanDTO) {
        return ci.applyForloan(loanDTO);
    }
    

//    for getting loan application in customer Dashboard
    @GetMapping("/loan-application")
    public LoanApplicationDetailsDTO getLoanApplications(@RequestParam String email) {
        return ci.getLoanApplicationsByEmail(email);
    }

    @DeleteMapping("/delete-loan")
    public String deleteLoanApplication(@RequestParam String email) {
        return ci.deleteLoanApplicationByEmail(email);
    }


    
}
