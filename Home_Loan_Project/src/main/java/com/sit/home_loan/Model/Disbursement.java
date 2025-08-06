package com.sit.home_loan.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "disbursement")
public class Disbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Double disbursementAmount;
	private LocalDate disbursementDate;
	private String disbursementStatus;
	
    @OneToOne
    @JsonBackReference("loan-disburse")
	private LoanApplication loanApplication;

}
