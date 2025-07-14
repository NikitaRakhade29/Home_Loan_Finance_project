// File: CustomerProfileDTO.java
package com.sit.home_loan.DTO;

import lombok.Data;

@Data
public class CustomerProfileDTO {
    private String name;
    private String email;
    private Long phone_no;
    private String address;
    private String employment_type;
    private String aadhaar_card;
    private String pan_card;
    private String account_number;
    private String ifsc_code;
    private Double montly_income;
    private String city;
    private String state;
    private String pincode;
    private Integer cibil;
}
