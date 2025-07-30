package com.sit.home_loan.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="loan-stage-histories")
public class LoanStageHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String updatedByRole;
	private String updatedByName;
	private String Stage;
	private String remarks;
	private LocalDateTime updatedAt;
	
	@ManyToOne
	private LoanApplication loanApplication;
	

}
