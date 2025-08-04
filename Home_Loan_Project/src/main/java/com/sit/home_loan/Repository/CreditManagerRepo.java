package com.sit.home_loan.Repository;

import java.util.List;

import com.sit.home_loan.Model.CustomerDocuments;

public interface CreditManagerRepo {
	List<CustomerDocuments> getByCustomer_Id(Long customerId);
}
