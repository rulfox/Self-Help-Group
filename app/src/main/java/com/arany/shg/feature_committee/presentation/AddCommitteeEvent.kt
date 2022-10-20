package com.arany.shg.feature_committee.presentation

import com.arany.shg.feature_member.data.model.Member

sealed class AddCommitteeEvent{
    data class SelectedMember(val member: Member): AddCommitteeEvent()
    data class SelectedDate(val date: String): AddCommitteeEvent()
    data class SelectedTime(val time: String): AddCommitteeEvent()
    object AddCommittee : AddCommitteeEvent()
}
