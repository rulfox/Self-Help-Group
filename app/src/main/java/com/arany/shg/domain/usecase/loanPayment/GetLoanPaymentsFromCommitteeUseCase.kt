package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.feature_loan.domain.repository.LoanPaymentRepository

class GetLoanPaymentsFromCommitteeUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(committeeId: Int) = loanPaymentRepository.getLoanPaymentsFromCommittee(committeeId)
}