package com.sit.home_loan.ServiceIMPL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Model.LoanStageHistory;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Repository.LoanStageHistoryRepo;
import com.sit.home_loan.Service.LoanStageHistoryI;
@Service
public class LoanStageHistoryIMPL implements LoanStageHistoryI {
	
	@Autowired
	LoanStageHistoryRepo lhr;
	
	@Autowired
	LoanApplicationRepo lr;
	
	@Override
	public void logStage(Long appId, String updateBy, String role, String stage, String remarks) {
		
		Optional<LoanApplication> optional = lr.findById(appId);
		if(optional.isPresent()) {
			LoanApplication application = optional.get();
			LoanStageHistory history = new LoanStageHistory();
			history.setLoanApplication(lr.findById(appId).get());
			history.setUpdatedByName(updateBy);
			history.setUpdatedByRole(role);
			history.setStage(stage);
			history.setRemarks(remarks);
			history.setUpdatedAt(LocalDateTime.now());
			lhr.save(history);
		}
	}
	
	@Override
	public List<LoanStageHistory> getLoanHistory(Long appId){
		List<LoanStageHistory> all = lhr.findAll();
		List<LoanStageHistory> result = new ArrayList<>();
		
		for(LoanStageHistory h: all) {
			if(h.getLoanApplication() !=null && h.getLoanApplication().getId().equals(appId)) {
				result.add(h);
			}
		}
		return result;
	}

}
