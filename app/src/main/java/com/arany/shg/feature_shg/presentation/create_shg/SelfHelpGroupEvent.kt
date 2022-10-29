package com.arany.shg.feature_shg.presentation.create_shg

sealed class SelfHelpGroupEvent {
    data class EnteredName(val shgName: String): SelfHelpGroupEvent()
    data class EnteredAddress(val address: String): SelfHelpGroupEvent()
    data class EnteredPassword(val password: String): SelfHelpGroupEvent()
    object CreateSelfHelpGroup: SelfHelpGroupEvent()
}