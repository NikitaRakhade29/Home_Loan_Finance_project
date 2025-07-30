package com.sit.home_loan.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.home_loan.Model.LoanStageHistory;

public interface LoanStageHistoryRepo extends JpaRepository<LoanStageHistory, Long> {
	List<LoanStageHistory> findByLoanApplicationId(Long LoanApplicationId);
}
