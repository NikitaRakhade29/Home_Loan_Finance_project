package com.sit.home_loan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.Enum.Roles;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Service.UserServiceI;

@RestController
@RequestMapping("/Auth")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserServiceI usi;

	@PostMapping(path = "/register")
	public String registerUser(@RequestBody User user) {
		return usi.register(user);
	}

	@PostMapping(path = "/login")
	public String userLogin(@RequestBody User login) {
		try {
			User user = usi.loginuser(login.getEmail(),login.getPassword());
			return "Login Successfully";
		} catch (Exception e) {
			return "Login Failed :(" + e.getMessage();
		}
	}

}
