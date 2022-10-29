package com.arany.shg.feature_committee.presentation

import com.arany.shg.feature_committee.data.model.CommitteeWithDetails
import com.arany.shg.feature_member.data.model.Member

sealed class CommitteeListingEvent{
    data class CommitteeCardClicked(val committeeWithDetails: CommitteeWithDetails): CommitteeListingEvent()
    object AddCommittee : CommitteeListingEvent()
}
