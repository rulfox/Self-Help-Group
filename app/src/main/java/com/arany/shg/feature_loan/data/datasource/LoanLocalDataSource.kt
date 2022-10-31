package com.arany.shg.feature_loan.data.datasource

import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.data.models.LoanWithDetails

interface LoanLocalDataSource {
    suspend fun createLoan(loan: Loan): Long
    suspend fun getLoansFromCommittee(committeeId: Int): List<LoanWithDetails>
    suspend fun getLoansFromSelfHelpGroup(shgId: Int): List<LoanWithDetails>
    suspend fun getLoan(loanId: Int): LoanWithDetails?
    suspend fun updateLoan(loan: Loan): Int
    suspend fun deleteLoan(loan: Loan): Int
}