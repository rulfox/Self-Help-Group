package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.feature_loan.data.models.LoanPayment
import com.arany.shg.feature_loan.domain.repository.LoanPaymentRepository

class UpdateLoanPaymentUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(loanPayment: LoanPayment) = loanPaymentRepository.updateLoanPayment(loanPayment)
}