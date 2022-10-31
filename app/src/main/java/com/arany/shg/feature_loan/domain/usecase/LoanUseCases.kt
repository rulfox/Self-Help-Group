package com.arany.shg.feature_loan.domain.usecase

import javax.inject.Inject

data class LoanUseCases @Inject constructor(
    val createLoanUseCase: CreateLoanUseCase,
    val deleteLoanUseCase: DeleteLoanUseCase,
    val getLoansFromCommitteeUseCase: GetLoansFromCommitteeUseCase,
    val getLoansFromSelfHelpGroupUseCase: GetLoansFromSelfHelpGroupUseCase,
    val getLoanUseCase: GetLoanUseCase,
    val updateLoanUseCase: UpdateLoanUseCase
)
