package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.domain.repository.LoanPaymentRepository

class GetLoanPaymentUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(loanPaymentId: Int) = loanPaymentRepository.getLoanPayment(loanPaymentId)
}