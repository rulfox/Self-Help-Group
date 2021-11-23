package com.arany.shg.domain.usecase.loan

import com.arany.shg.data.*
import com.arany.shg.domain.repository.*

class GetLoansFromSelfHelpGroupUseCase(private val loanRepository: LoanRepository) {
    suspend fun execute(shgId: Int) = loanRepository.getLoansFromSelfHelpGroup(shgId)
}