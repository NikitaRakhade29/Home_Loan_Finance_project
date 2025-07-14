package com.sit.home_loan.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sit.home_loan.Enum.Roles;
import com.sit.home_loan.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByEmail (String Email);
	boolean existsByRole(Roles Role);
}
