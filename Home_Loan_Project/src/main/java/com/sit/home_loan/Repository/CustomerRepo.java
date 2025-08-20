package com.sit.home_loan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.User;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long> {
	Optional<Customers> findByUserEmail(String email);
//	Optional<Customers> findByEmail(String email);
}
