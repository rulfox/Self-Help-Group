package com.arany.shg.feature_dashboard.presentation

import com.arany.shg.feature_dashboard.data.models.Services

sealed class DashboardEvent {
    data class ServiceCardClicked(val service: Services): DashboardEvent()
}