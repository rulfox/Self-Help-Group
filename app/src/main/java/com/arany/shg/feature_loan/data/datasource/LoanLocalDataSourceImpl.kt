package com.arany.shg.feature_loan.data.datasource

import com.arany.shg.data.db.LoanDAO
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.data.models.LoanWithDetails
import javax.inject.Inject

class LoanLocalDataSourceImpl @Inject constructor(private val loanDao: LoanDAO): LoanLocalDataSource {
    override suspend fun createLoan(loan: Loan): Long {
        return loanDao.addLoan(loan)
    }

    override suspend fun getLoansFromCommittee(committeeId: Int): List<LoanWithDetails> {
        return loanDao.getLoansOfCommittee(committeeId)
    }

    override suspend fun getLoansFromSelfHelpGroup(shgId: Int): List<LoanWithDetails> {
        return loanDao.getLoans(shgId)
    }

    override suspend fun getLoan(loanId: Int): LoanWithDetails? {
        return loanDao.getLoan(loanId)
    }

    override suspend fun updateLoan(loan: Loan): Int {
        return loanDao.updateLoan(loan)
    }

    override suspend fun deleteLoan(loan: Loan): Int {
        return loanDao.deleteLoan(loan)
    }
}