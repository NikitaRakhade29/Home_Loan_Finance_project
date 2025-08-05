package com.sit.home_loan.ServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sit.home_loan.DTO.LoanWithDocumentsDTO;
import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Enum.VerificationStatus;
import com.sit.home_loan.Model.CreditEvalution;
import com.sit.home_loan.Model.CustomerDocuments;
import com.sit.home_loan.Model.Customers;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Repository.CreditEvalutionRepo;
import com.sit.home_loan.Repository.CreditManagerRepo;
import com.sit.home_loan.Repository.DocumentRepo;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Service.CreditManagerI;
import com.sit.home_loan.Service.LoanStageHistoryI;

@Service
public class CreditManagerIMPL implements CreditManagerI {

	@Autowired
	LoanApplicationRepo lr;

	@Autowired
	DocumentRepo dr;

	@Autowired
	CreditManagerRepo cmr;

	@Autowired
	LoanStageHistoryI li;

	@Autowired
	CreditEvalutionRepo cer;

	@Override
	public List<LoanApplication> getApplicationsWithDocumentsSumbmitted() {
		return lr.findByApplicationStatus(ApplicationStatus.DOCUMENT_UPLOADED);
	}

	@Override
	public LoanWithDocumentsDTO getLoanWithDocuments(long loanAppId) {
		LoanWithDocumentsDTO dto = new LoanWithDocumentsDTO();

		LoanApplication loan = lr.findById(loanAppId).orElse(null);
		if (loan == null) {
			return null;
		}

		Customers cust = loan.getCustomer();
		List<CustomerDocuments> docs = new ArrayList<>();

		if (cust != null) {
			docs = dr.getByCustomer_CustId(cust.getCustId());
		}

		dto.setLoan(loan);
		dto.setDocuments(docs);
		return dto;
	}

	@Override
	public void updateVerificationStatus(Long documentId, String status) {
		CustomerDocuments documents = cmr.findById(documentId).orElse(null);

		if (documents == null) {
			throw new RuntimeException("Document not found");
		}

		documents.setVerificationStatus(VerificationStatus.valueOf(status.toUpperCase()));
		cmr.save(documents);
		LoanApplication app = documents.getCustomer().getLoanApplications().get(0);

		li.logStage(app.getId(), "credit Manager", "CREDIT_MANAGER", ApplicationStatus.DOCUMENT_UPLOADED.name(),
				"Verified Document" + documents.getDocumentType());

	}

	@Override
	public void evaluateLoanApplication(long loanAppId) {
		Optional<LoanApplication> loanOpt = lr.findById(loanAppId);

		if (!loanOpt.isPresent()) {
			throw new RuntimeException("Loan Application Not Found");
		}

		LoanApplication loanApp = loanOpt.get();
		long customerId = loanApp.getCustomer().getCustId();
		List<CustomerDocuments> doclist = cmr.getByCustomer_CustId(customerId);
		boolean allVerified = true;

		for (int i = 0; i < doclist.size(); i++) {
			CustomerDocuments doc = doclist.get(i);

			if (!doc.getVerificationStatus().equals(VerificationStatus.VERIFIED)) {
				allVerified = false;
				break;
			}
		}
		if (!allVerified) {
			throw new RuntimeException("All documents must be VERIFIED before evalution");
		}

		CreditEvalution eval = new CreditEvalution();
		eval.setLoanApplication(loanApp);

		double income = loanApp.getCustomer().getMontly_income();
		double loanAmount = loanApp.getLoan_amount();
		double dtiRatio = loanAmount / (income * 12);

		eval.setDebtToIncomeRatio(dtiRatio);
		eval.setApprovedAmount(loanAmount);
		eval.setInterestRate(10.5);
		eval.setEvalutionRemarks("Eligible based on verified documents and income");
		eval.setEvalutionStatus("APPROVED");

		cer.save(eval);

		loanApp.setApplicationStatus(ApplicationStatus.EVALUATED);
		loanApp.setCreditEvaluation(eval);
		lr.save(loanApp);

		li.logStage(loanApp.getId(), "credit Manager", "CREDIT_MANAGER", ApplicationStatus.EVALUATED.name(),
				"Loan eligibity evaluted by credit manager");
	}

}
