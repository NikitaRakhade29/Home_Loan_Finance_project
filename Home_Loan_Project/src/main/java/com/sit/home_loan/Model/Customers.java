package com.sit.home_loan.Model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
	private Long custId;
	private String full_name;
	
	@Column(unique = true)
	private String email;
	private Long phone_no;
	private String address;
	private String employment_type;
	private String aadhaar_card;
	private String pan_card;
	private String account_number;
	private String ifsc_code;
	private String Kyc_Status;
	private Double montly_income;
	private String city;
	private String State;
	private String pincode;
	private Integer cibil;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "user_id")
	@JsonManagedReference
	private User user;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<LoanApplication> loanApplications;
	

}
