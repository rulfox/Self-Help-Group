package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.data.*
import com.arany.shg.domain.repository.*

class GetLoanPaymentUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(loanPaymentId: Int) = loanPaymentRepository.getLoanPayment(loanPaymentId)
}