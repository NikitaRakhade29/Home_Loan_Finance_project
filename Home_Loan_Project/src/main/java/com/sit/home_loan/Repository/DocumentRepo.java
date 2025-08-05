package com.sit.home_loan.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Model.CustomerDocuments;

@Repository
public interface DocumentRepo extends JpaRepository<CustomerDocuments, Long> {
	List<CustomerDocuments> findByCustomer_Email(String email);
	List<CustomerDocuments> getByCustomer_CustId(Long custId);
}
