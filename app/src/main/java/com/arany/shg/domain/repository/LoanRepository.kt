package com.arany.shg.domain.repository

import com.arany.shg.data.models.Loan
import com.arany.shg.data.util.Resource

interface LoanRepository{
      suspend fun createLoan(loan: Loan)
      suspend fun getLoansFromCommittee(committeeId: Int): Resource<ArrayList<Loan>>
      suspend fun getLoansFromSelfHelpGroup(committeeId: Int): Resource<ArrayList<Loan>>
      suspend fun getLoan(loanId: Int): Resource<Loan>
      suspend fun updateLoan(loan: Loan)
      suspend fun deleteLoan(loan: Loan)
}