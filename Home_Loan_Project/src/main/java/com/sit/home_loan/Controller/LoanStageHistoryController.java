package com.sit.home_loan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sit.home_loan.Model.LoanStageHistory;
import com.sit.home_loan.Service.LoanStageHistoryI;

@RestController
@RequestMapping("/loan-history")
@CrossOrigin("*")
public class LoanStageHistoryController {

	@Autowired
	LoanStageHistoryI lhi;

	@PostMapping("/log")
	public String logStage(@RequestParam Long appId, @RequestParam String updateBy, @RequestParam String role,
			@RequestParam String stage, @RequestParam(required = false) String remarks) {
		lhi.logStage(appId, updateBy, role, stage, remarks);
		return "Stage logged successfully";
	}

	@GetMapping("/get")
	public List<LoanStageHistory> getLoanHistory(@RequestParam Long appId) {
		return lhi.getLoanHistory(appId);
	}

}
