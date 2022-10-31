package com.arany.shg.feature_loan.domain.repository

import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_loan.data.models.LoanWithDetails

interface LoanRepository{
      suspend fun createLoan(loan: Loan): Long
      suspend fun getLoansFromCommittee(committeeId: Int): Resource<List<LoanWithDetails>>
      suspend fun getLoansFromSelfHelpGroup(shgId: Int): Resource<List<LoanWithDetails>>
      suspend fun getLoan(loanId: Int): Resource<LoanWithDetails>
      suspend fun updateLoan(loan: Loan): Int
      suspend fun deleteLoan(loan: Loan): Int
}