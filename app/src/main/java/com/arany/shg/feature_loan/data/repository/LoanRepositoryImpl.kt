package com.arany.shg.feature_loan.data.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_loan.data.datasource.LoanLocalDataSource
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.data.models.LoanWithDetails
import com.arany.shg.feature_loan.domain.repository.LoanRepository
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor( private val loanLocalDataSource: LoanLocalDataSource): LoanRepository {
    override suspend fun createLoan(loan: Loan): Long {
        return loanLocalDataSource.createLoan(loan)
    }

    override suspend fun getLoansFromCommittee(committeeId: Int): Resource<List<LoanWithDetails>> {
        return Resource.Success(loanLocalDataSource.getLoansFromCommittee(committeeId))
    }

    override suspend fun getLoansFromSelfHelpGroup(shgId: Int): Resource<List<LoanWithDetails>> {
        return Resource.Success(loanLocalDataSource.getLoansFromSelfHelpGroup(shgId))
    }

    override suspend fun getLoan(loanId: Int): Resource<LoanWithDetails> {
        return loanLocalDataSource.getLoan(loanId)?.let { Resource.Success(it) } ?: run { Resource.Error("No such Loans found")}
    }

    override suspend fun updateLoan(loan: Loan): Int {
        return loanLocalDataSource.updateLoan(loan)
    }

    override suspend fun deleteLoan(loan: Loan): Int {
        return loanLocalDataSource.deleteLoan(loan)
    }
}