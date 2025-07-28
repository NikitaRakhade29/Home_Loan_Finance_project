package com.sit.home_loan.DTO;

import java.time.LocalDate;

import com.sit.home_loan.Enum.ApplicationStatus;

import lombok.Data;

@Data
public class LoanApplicationDetailsDTO {
	private String email;
    private Double loan_amount;
    private Integer loan_tenure;
    private String loan_purpose;
    private String pan_no;
    private String aadhar;
    private String employment_type;
    private String employment_name;
    private Double monthly_income;
    private Integer cibil;
    private String account_no;
    private String ifsc_code;
    private String account_holder_name;
    private String application_rejection_reason;
    private LocalDate application_date;
    private ApplicationStatus applicationstatus;
}
