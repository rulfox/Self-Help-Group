package com.arany.shg.domain.usecase.loanPayment

import com.arany.shg.feature_loan.domain.repository.LoanPaymentRepository

class GetLoanPaymentsFromSelfHelpGroupUseCase(private val loanPaymentRepository: LoanPaymentRepository) {
    suspend fun execute(shgId: Int) = loanPaymentRepository.getLoanPaymentsFromSelfHelpGroup(shgId)
}