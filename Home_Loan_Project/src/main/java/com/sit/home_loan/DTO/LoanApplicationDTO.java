package com.sit.home_loan.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanApplicationDTO {
	private String email;
    private Double loan_amount;
    private Integer loan_tenure;
    private String loan_purpose;
    private String pan_no;
    private String aadhar;
    private String employment_type;
    private String employment_name;
    private Double monthly_income;
    private String account_no;
    private String ifsc_code;
    private String account_holder_name;
    private String application_reject_reason;
}
