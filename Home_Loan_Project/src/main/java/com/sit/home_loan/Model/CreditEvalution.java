package com.sit.home_loan.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "credit_evalution")
public class CreditEvalution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double debtToIncomeRatio;
	private double approvedAmount;
	private double interestRate;
	private String evalutionRemarks;
	private String evalutionStatus;
	
	@OneToOne
	private LoanApplication loanApplication;

}
