package com.sit.home_loan.Service;

import java.util.List;

import com.sit.home_loan.Model.LoanStageHistory;

public interface LoanStageHistoryI {
    void logStage(Long appId, String updateBy, String role, String stage, String remarks);
    List<LoanStageHistory> getLoanHistory(Long appId);

}
