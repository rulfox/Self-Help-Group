package com.arany.shg.feature_loan.domain.repository

import com.arany.shg.feature_loan.data.models.LoanPayment
import com.arany.shg.data.util.Resource

interface LoanPaymentRepository{
      suspend fun createLoanPayment(loanPayment: LoanPayment)
      suspend fun getLoanPaymentsFromCommittee(committeeId: Int): Resource<ArrayList<LoanPayment>>
      suspend fun getLoanPaymentsFromSelfHelpGroup(committeeId: Int): Resource<ArrayList<LoanPayment>>
      suspend fun getLoanPayment(loanPaymentId: Int): Resource<LoanPayment>
      suspend fun updateLoanPayment(loanPayment: LoanPayment)
      suspend fun deleteLoanPayment(loanPayment: LoanPayment)
}