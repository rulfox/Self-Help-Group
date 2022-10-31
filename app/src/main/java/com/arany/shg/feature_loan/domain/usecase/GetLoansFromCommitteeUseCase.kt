package com.arany.shg.feature_loan.domain.usecase

import com.arany.shg.feature_loan.domain.repository.LoanRepository

class GetLoansFromCommitteeUseCase(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(committeeId: Int) = loanRepository.getLoansFromCommittee(committeeId)
}