package com.arany.shg.domain.usecase.loan

import com.arany.shg.domain.repository.LoanRepository

class GetLoanUseCase(private val loanRepository: LoanRepository) {
    suspend fun execute(loanId: Int) = loanRepository.getLoan(loanId)
}