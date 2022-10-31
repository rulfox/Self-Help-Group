package com.arany.shg.feature_loan.domain.usecase

import com.arany.shg.feature_loan.domain.repository.LoanRepository

class GetLoanUseCase(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(loanId: Int) = loanRepository.getLoan(loanId)
}