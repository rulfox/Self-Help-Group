package com.arany.shg.domain.usecase.loan

import com.arany.shg.domain.repository.LoanRepository

class GetLoansFromSelfHelpGroupUseCase(private val loanRepository: LoanRepository) {
    suspend fun execute(shgId: Int) = loanRepository.getLoansFromSelfHelpGroup(shgId)
}