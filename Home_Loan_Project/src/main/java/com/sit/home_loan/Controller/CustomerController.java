package com.sit.home_loan.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sit.home_loan.DTO.LoanApplicationDTO;
import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
import com.sit.home_loan.Enum.DocumentType;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.CustomerRepo;
import com.sit.home_loan.Service.CustomerI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return ci.getProfile(email);
	}

	@PostMapping("/edit")
	public String editProfile(@RequestParam String email, @RequestBody Customers updateCust) {
		ci.editProfileByEmail(email, updateCust);
		return "Profile updated successfully.";
	}

	@PostMapping(path = "/apply-loan")
	public String applyLoan(@RequestBody LoanApplicationDTO loanDTO) {
		return ci.applyForloan(loanDTO);
	}

//  for getting loan application in customer Dashboard
	@GetMapping("/loan-application")
	public List<LoanApplicationDetailsDTO> getLoanApplications(@RequestParam String email) {
		return ci.getLoanApplications(email);
	}

	@DeleteMapping("/delete-loan")
	public String deletetheLoanApplication(@RequestParam String email) {
		return ci.deleteLoanApplication(email);
	}
	
	@PostMapping(path="/upload-documents")
	public String uploadDocument(@RequestParam ("file") MultipartFile file, @RequestParam ("email") String email, @RequestParam ("type") DocumentType documentType) {
		return ci.uploadDocument(file, email, documentType);	
	}
	
	@GetMapping("/my-documents")
	public List<CustomerDocuments> getDocuments(@RequestParam String email) {
		return ci.myDocuments(email);
	}
	
	
}
