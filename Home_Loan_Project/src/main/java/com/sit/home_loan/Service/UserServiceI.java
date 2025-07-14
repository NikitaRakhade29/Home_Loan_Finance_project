package com.sit.home_loan.Service;


import com.sit.home_loan.Enum.Roles;
import com.sit.home_loan.Model.User;

public interface UserServiceI {
	String register(User user);
	User loginuser(String email, String password, Roles role);
}
