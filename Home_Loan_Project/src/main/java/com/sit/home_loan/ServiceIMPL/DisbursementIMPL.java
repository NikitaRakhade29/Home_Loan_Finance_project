package com.sit.home_loan.ServiceIMPL;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Model.Disbursement;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Repository.DisbursementRepo;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Service.DisbursementI;
import com.sit.home_loan.Service.LoanStageHistoryI;

@Service
public class DisbursementIMPL implements DisbursementI {

	@Autowired
	DisbursementRepo dr;

	@Autowired
	LoanStageHistoryI lhi;

	@Autowired
	LoanApplicationRepo lr;

	@Override
	public List<LoanApplication> getSanctionedApplications() {
		return lr.findByApplicationStatus(ApplicationStatus.SANCTIONED);
	}

	@Override
	public String disbursementLoan(Long loanAppId, Double amount) {
		Optional<LoanApplication> loanOpt = lr.findById(loanAppId);
		if (loanOpt.isEmpty()) {
			return "Loan Application not Found";
		}
		LoanApplication loanApp = loanOpt.get();

		if (dr.findByLoanApplication(loanApp).isPresent()) {
			return "Disbursement already exists for this Loan Application";
		}

		Disbursement dis = new Disbursement();
		dis.setLoanApplication(loanApp);
		dis.setDisbursementAmount(amount);
		dis.setDisbursementDate(LocalDate.now());
		dis.setDisbursementStatus("DISBURSED");

		dr.save(dis);

		loanApp.setApplicationStatus(ApplicationStatus.DISBURSED);
		lr.save(loanApp);

		lhi.logStage(loanApp.getId(), "Disbursement Manager", "DISBURSEMENT_MANAGER", ApplicationStatus.DISBURSED.name(),
				"Loan disbursed Successfully");
		return "Loan Disbursed Successfully for loanApplication ID: " + loanAppId;
	}

}
