package com.sit.home_loan.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sit.home_loan.Model.CustomerDocuments;

public interface CreditManagerRepo extends JpaRepository<CustomerDocuments, Long>{
	List<CustomerDocuments> getByCustomer_CustId(Long custId);
}
