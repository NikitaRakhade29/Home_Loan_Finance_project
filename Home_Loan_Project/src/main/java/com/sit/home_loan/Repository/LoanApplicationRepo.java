package com.sit.home_loan.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Model.LoanAppliaction;

@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanAppliaction, Long> {
}

