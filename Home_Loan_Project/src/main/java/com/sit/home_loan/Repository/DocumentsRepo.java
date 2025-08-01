package com.sit.home_loan.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Model.CustomerDocuments;

public interface DocumentsRepo extends JpaRepository<CustomerDocuments, Long> {
	List<CustomerDocuments> findByCustomerEmail(String email);
	List<CustomerDocuments> findByCustomer_CustId(Long custId);
}
