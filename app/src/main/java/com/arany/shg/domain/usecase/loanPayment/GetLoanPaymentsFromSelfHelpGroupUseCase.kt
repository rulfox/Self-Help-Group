package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.data.*
import com.arany.shg.domain.repository.*

class GetLoanPaymentsFromSelfHelpGroupUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(shgId: Int) = loanPaymentRepository.getLoanPaymentsFromSelfHelpGroup(shgId)
}