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
@Table(name = "sanction_letters")
public class SanctionLetter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate issueDate;
	private Double sanctionAmount;
	private double interestRate;
	private Integer tendureInMonths;
	private String emiScheduleFileUrl;
	private String bankName="Zenith Savings & Loans";
	private String bankBranch="Tech Park Branch";
	
	@OneToOne
	@JsonBackReference("loan-sanction")
	private LoanApplication loanApplication;

}
