package com.sit.home_loan.Model;

import java.text.DecimalFormat;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sit.home_loan.Enum.ApplicationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Loan-Application")
public class LoanApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double loan_amount;
	private Integer loan_tenure;
	private String loan_purpose;
	private String pan_no;
	private String aadhar;
	private String employment_type;
	private String employment_name;
	private Double monthly_income;
	private Integer Cibil;
	private String account_no;
	private String ifsc_code;
	private String account_holder_name;
	private String application_rejection_reason;
	private LocalDate application_date;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customers customer;

	@OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL)
	@JsonManagedReference("loan-credit")
	private CreditEvalution creditEvaluation;

	@OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL)
	@JsonManagedReference("loan-sanction")
	private SanctionLetter sanctionLetter;
	
    @OneToOne(mappedBy = "loanApplication", cascade = CascadeType.ALL)
    @JsonManagedReference("loan-disburse")
	private Disbursement disbursement;

}
