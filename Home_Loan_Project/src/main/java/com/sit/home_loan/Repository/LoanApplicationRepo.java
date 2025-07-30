package com.sit.home_loan.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Model.LoanApplication;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication, Long> {
	List<LoanApplication> findByCustomerEmail(String email);
	List<LoanApplication> findByApplicationStatus(ApplicationStatus status);

}

