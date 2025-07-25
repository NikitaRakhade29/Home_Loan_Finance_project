package com.sit.home_loan.Service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.home_loan.Enum.Roles;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.CustomerRepo;
import com.sit.home_loan.Repository.UserRepo;

@Service
public class UserServiceIMPL implements UserServiceI {

	@Autowired
	UserRepo ur;
	
	@Autowired
	CustomerRepo cr;

	@Override
	public String register(User user) {

// CHECK EMAIL IS ALREADY REGISERTED OR NOT
//		System.out.println(user.getEmail());
		Optional<User> existuser = ur.findByEmail(user.getEmail());
		if (existuser.isPresent()) {
			return "This user is already registered...";
		}

// ONLY ALLOW TO CREATE MULTIPLE CUSTOMERS AND ONLY SINGLE OFFICER
		if (user.getRole() == Roles.Loan_Officer || user.getRole() == Roles.Credit_Manager
				|| user.getRole() == Roles.Disbursement_Manager) {

			boolean checkrole = ur.existsByRole(user.getRole());
			if (checkrole) {
				return "Only one user allowed for the role: " + user.getRole();
			}
		}

// CHECK THE ROLE OF USER IF NULL SHOW MESSAGE
		if (user.getRole() == null) {
			return "User role is missing!";
		}

//ENCRYPT THE PASSWORD
		String passencrypt = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(passencrypt);

		ur.save(user);
//
//		if (user.getRole() == Roles.Customer) {
//			Customers customer = new Customers();
//			customer.setUser(user);
//			cr.save(customer);
//		}
		
	    if (user.getRole() == Roles.Customer) {
	        Customers customer = new Customers();
	        customer.setUser(user);
	        customer.setFull_name(user.getFull_name());
	        customer.setEmail(user.getEmail()); 
	        customer.setPhone_no(user.getPhone_no());
	        customer.setAddress(user.getAddress());
	        cr.save(customer);
	    }

// SAVE THE USER
		ur.save(user);
		return "Registered user role is " + user.getRole();

	}

	@Override
	public User loginuser(String email, String password, Roles role) {
		Optional<User> checkEmail = ur.findByEmail(email);

		if (!checkEmail.isPresent()) {
			throw new RuntimeException("User not found.");
		}

		User user = checkEmail.get();

		if (!BCrypt.checkpw(password, user.getPassword())) {
			throw new RuntimeException("Incorrect password.");
		}

		if (!role.equals(user.getRole())) {
			throw new RuntimeException("Invalid role :( Try again ");
		}
		return user;
	}

}
