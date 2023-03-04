package com.arany.shg.feature_thrift.presentation

import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member

sealed class AddThriftEvent{
    data class EnteredThriftAmount(val amount: String): AddThriftEvent()
    data class SelectedCommittee(val committee: Committee): AddThriftEvent()
    data class SelectedMember(val member: Member): AddThriftEvent()
    object HideThriftAddedAlertDialog : AddThriftEvent()
    object ShowThriftAddedAlertDialog : AddThriftEvent()
    object AddThrift : AddThriftEvent()
    object ClearScreen : AddThriftEvent()
}
