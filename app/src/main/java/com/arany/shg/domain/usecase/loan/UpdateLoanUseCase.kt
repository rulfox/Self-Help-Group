package com.arany.shg.domain.usecase.loan

import com.arany.shg.data.models.Loan
import com.arany.shg.domain.repository.*

class UpdateLoanUseCase(private val loanRepository: LoanRepository) {
    suspend fun execute(loan: Loan) = loanRepository.updateLoan(loan)
}