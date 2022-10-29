package com.arany.shg.feature_committee.presentation

import com.arany.shg.feature_dashboard.data.models.Services

sealed class CommitteeDetailsEvent {
    data class ServiceCardClicked(val service: Services): CommitteeDetailsEvent()
}