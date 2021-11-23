package com.arany.shg.domain.usecase.loan

import com.arany.shg.data.*
import com.arany.shg.domain.repository.*

class GetLoansFromCommitteeUseCase(private val loanRepository: LoanRepository) {
    suspend fun execute(committeeId: Int) = loanRepository.getLoansFromCommittee(committeeId)
}