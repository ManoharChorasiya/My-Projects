package com.info.emicalc.dao;

import java.util.List;

import com.info.emicalc.entity.LoanRecord;

public interface LoanDAO {
	boolean saveLoan(LoanRecord lr);

	List<LoanRecord> getLoansByUser(int userId);

	boolean deleteLoan(int loanId);
}