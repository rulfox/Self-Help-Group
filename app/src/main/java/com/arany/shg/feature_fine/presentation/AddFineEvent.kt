package com.arany.shg.feature_fine.presentation

import com.arany.shg.feature_fine.data.models.FineType
import com.arany.shg.feature_member.data.model.Member

sealed class AddFineEvent{
    data class EnteredFineAmount(val amount: String): AddFineEvent()
    data class SelectedMember(val member: Member): AddFineEvent()
    data class SelectedFineType(val fineType: FineType): AddFineEvent()
    object ClearScreen: AddFineEvent()
    object HideFineAddedAlertDialog : AddFineEvent()
    object ShowFineAddedAlertDialog : AddFineEvent()
    object AddFine : AddFineEvent()
}
