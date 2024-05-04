package com.arany.shg.feature_fine.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.*
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.toFormattedString
import com.arany.shg.data.util.DateUtils.toString
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import com.arany.shg.feature_committee.presentation.AddCommitteeViewModel
import com.arany.shg.feature_fine.data.models.Fine
import com.arany.shg.feature_fine.data.models.FineType
import com.arany.shg.feature_fine.domain.usecase.FineUseCases
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.domain.usecase.LoanUseCases
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import com.arany.shg.feature_thrift.data.model.Thrift
import com.arany.shg.feature_thrift.domain.usecase.ThriftUseCases
import com.arany.shg.feature_thrift.presentation.AddThriftEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFineViewModel @Inject constructor(
    private val fineUseCases: FineUseCases,
    private val memberUseCases: MemberUseCases,
    state: SavedStateHandle
): ViewModel() {

    private var committeeId: Int = state.get<Int>(Constants.NAV_ARG_COMMITTEE_ID)!!

    var members = MutableStateFlow<List<Member>>(arrayListOf())
        private set

    var fineTypes = MutableStateFlow<List<FineType>>(arrayListOf())
        private set

    var fineAmount = MutableStateFlow(TextFieldState(hint = "Enter Fine Amount"))
        private set

    var member = MutableStateFlow(MemberState(hint = "Select Member"))
    private set

    var fineType = MutableStateFlow(FineTypeState(hint = "Select Fine Type"))
        private set

    var fineAddedAlertDialog = MutableStateFlow(AlertDialogState(title = "Fine Added", description = "Your Fine amount has been updated. You can check your profile to know your fine updated amount", isShown = false))
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val addLoanExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowError(throwable.message?:"Couldn't add Loan"))
        }
    }

    init {
        viewModelScope.launch {
            memberUseCases.getMembersByShgIdUseCase(Constants.ShgId).collect{
                it.forEach {
                    Log.e("Members", it.toString())
                }
                members.value = it
            }
        }
    }

    fun onEvent(event: AddFineEvent) {
        when(event){
            is AddFineEvent.EnteredFineAmount -> {
                fineAmount.value = fineAmount.value.copy(text = event.amount)
            }
            is AddFineEvent.SelectedMember -> {
                member.value = member.value.copy(member = event.member)
            }
            is AddFineEvent.SelectedFineType -> {
                fineType.value = fineType.value.copy(fineType = event.fineType)
            }
            is AddFineEvent.HideFineAddedAlertDialog -> {
                fineAddedAlertDialog.value = fineAddedAlertDialog.value.copy(isShown = false)
            }
            is AddFineEvent.ShowFineAddedAlertDialog -> {
                fineAddedAlertDialog.value = fineAddedAlertDialog.value.copy(isShown = true)
            }
            is AddFineEvent.ClearScreen -> {
                fineAmount.value = fineAmount.value.copy(text = "")
                member.value = member.value.copy(member = null)
                fineType.value = fineType.value.copy(fineType = null)
            }
            is AddFineEvent.AddFine -> {
                viewModelScope.launch(Dispatchers.IO + addLoanExceptionHandler) {
                    if(null == member.value.member)
                        _eventFlow.emit(UiEvent.ShowError("Select member"))
                    else if(null == fineType.value.fineType)
                        _eventFlow.emit(UiEvent.ShowError("Select fine type"))
                    else if(fineAmount.value.text.isEmpty())
                        _eventFlow.emit(UiEvent.ShowError("Enter loan amount"))
                    else{
                        fineUseCases.addFineUseCase(
                            Fine(
                                fineId = null,
                                committeeId = committeeId,
                                memberId = member.value.member?.memberId,
                                amount = fineAmount.value.text.toDoubleOrNull()?: 0.0,
                                dateTime = DateUtils.getCurrentDateTime().toFormattedString(),
                                statusId = 0,
                                fineTypeId = fineType.value.fineType?.fineTypeId
                            )
                        )
                        _eventFlow.emit(UiEvent.LoanAdded)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object LoanAdded: UiEvent()
    }
}