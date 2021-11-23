package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.data.*
import com.arany.shg.domain.repository.*

class GetLoanPaymentsFromCommitteeUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(committeeId: Int) = loanPaymentRepository.getLoanPaymentsFromCommittee(committeeId)
}