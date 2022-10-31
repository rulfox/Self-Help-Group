package com.arany.shg.feature_loan.presentation

import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member

sealed class AddLoanEvent{
    data class EnteredLoanAmount(val amount: String): AddLoanEvent()
    data class SelectedMember(val member: Member): AddLoanEvent()
    object AddLoan : AddLoanEvent()
}
