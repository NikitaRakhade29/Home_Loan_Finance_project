package com.sit.home_loan.ServiceIMPL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sit.home_loan.DTO.LoanApplicationDetailsDTO;
import com.sit.home_loan.Enum.ApplicationStatus;
import com.sit.home_loan.Model.LoanApplication;
import com.sit.home_loan.Model.User;
import com.sit.home_loan.Repository.LoanApplicationRepo;
import com.sit.home_loan.Repository.UserRepo;
import com.sit.home_loan.Service.LoanOfficerI;
import com.sit.home_loan.Service.LoanStageHistoryI;

@Service
public class LoanOfficerIMPL implements LoanOfficerI {

	@Autowired
	LoanApplicationRepo lr;

	@Autowired
	UserRepo ur;

	@Autowired
	LoanStageHistoryI lhi;

	@Override
	public List<LoanApplicationDetailsDTO> getAllPendingApplications() {
		List<LoanApplication> applications = lr.findByApplicationStatus(ApplicationStatus.PENDING);

		List<LoanApplicationDetailsDTO> dtoList = new ArrayList<>();
		for (LoanApplication app : applications) {
			LoanApplicationDetailsDTO dto = new LoanApplicationDetailsDTO();
			dto.setEmail(app.getCustomer().getEmail());
			dto.setLoan_amount(app.getLoan_amount());
			dto.setLoan_tenure(app.getLoan_tenure());
			dto.setLoan_purpose(app.getLoan_purpose());
			dto.setPan_no(app.getPan_no());
			dto.setAadhar(app.getAadhar());
			dto.setEmployment_type(app.getEmployment_type());
			dto.setEmployment_name(app.getEmployment_name());
			dto.setMonthly_income(app.getMonthly_income());
			dto.setCibil(app.getCibil());
			dto.setAccount_no(app.getAccount_no());
			dto.setIfsc_code(app.getIfsc_code());
			dto.setAccount_holder_name(app.getAccount_holder_name());
			dto.setApplication_date(app.getApplication_date());
			dto.setApplicationstatus(app.getApplicationStatus());

			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public String reviewCibilDecision(Long applicationId, String officerEmail, Boolean reject, String reasonIfReject) {
		Optional<LoanApplication> application = lr.findById(applicationId);
		if (application.isEmpty()) {
			return "loan application not found";
		}

		LoanApplication app = application.get();
		if (app.getApplicationStatus() != ApplicationStatus.PENDING) {
			return "Application is not in pending state";
		}

		Optional<User> officers = ur.findByEmail(officerEmail);
		if (officers.isEmpty()) {
			return "officer not found";
		}

		User officer = officers.get();

		if (reject) {
			app.setApplicationStatus(ApplicationStatus.REJECTED);
			app.setApplication_rejection_reason(reasonIfReject);
			lhi.logStage(app.getId(), officer.getFull_name(), officer.getRole().name(),
					ApplicationStatus.REJECTED.name(), "loan rejacted due to: " + reasonIfReject);
			lr.save(app);
			return "Application Rejected!";
		} else {
			app.setApplicationStatus(ApplicationStatus.UPLOAD_DOCUMENTS);
			lhi.logStage(app.getId(), officer.getFull_name(), officer.getRole().name(),
					ApplicationStatus.UPLOAD_DOCUMENTS.name(), "CIBIL check passed, requested document upload");
			lr.save(app);
			return "CIBIL Reviewed...Requested for upload documents";
		}
	}

}
