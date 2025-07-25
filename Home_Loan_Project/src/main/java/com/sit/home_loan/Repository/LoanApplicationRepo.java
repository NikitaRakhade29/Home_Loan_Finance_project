package com.sit.home_loan.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Model.LoanAppliaction;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanAppliaction, Long> {
	List<LoanAppliaction> findByCustomerEmail(String email);
}

