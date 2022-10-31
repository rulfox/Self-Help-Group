package com.arany.shg.feature_loan.domain.usecase

import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.domain.repository.LoanRepository

class DeleteLoanUseCase(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(loan: Loan) = loanRepository.deleteLoan(loan)
}