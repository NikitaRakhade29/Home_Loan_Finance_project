package com.sit.home_loan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Model.Disbursement;
import com.sit.home_loan.Model.LoanApplication;

@Repository
public interface DisbursementRepo extends JpaRepository<Disbursement, Long> {
	Optional<Disbursement> findByLoanApplication(LoanApplication loanApplication);
}
