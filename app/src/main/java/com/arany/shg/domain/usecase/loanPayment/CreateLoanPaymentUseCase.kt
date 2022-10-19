package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.data.models.LoanPayment
import com.arany.shg.domain.repository.LoanPaymentRepository

class CreateLoanPaymentUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(loanPayment: LoanPayment) = loanPaymentRepository.createLoanPayment(loanPayment)
}