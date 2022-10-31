package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.feature_loan.domain.repository.LoanPaymentRepository

class GetLoanPaymentUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(loanPaymentId: Int) = loanPaymentRepository.getLoanPayment(loanPaymentId)
}