package com.sit.home_loan.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.home_loan.Model.CustomerDocuments;

public interface DocumentRepo extends JpaRepository<CustomerDocuments, Long> {
	List<CustomerDocuments> findByCustomer_Email(String email);
	List<CustomerDocuments> findByCustomer_CustId(Long custId);
}
