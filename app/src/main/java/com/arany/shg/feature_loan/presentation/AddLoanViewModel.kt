package com.arany.shg.feature_loan.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.CommitteeState
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.MemberState
import com.arany.shg.core.util.TextFieldState
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.toFormattedString
import com.arany.shg.data.util.DateUtils.toString
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import com.arany.shg.feature_committee.presentation.AddCommitteeViewModel
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_loan.domain.usecase.LoanUseCases
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import com.arany.shg.feature_thrift.data.model.Thrift
import com.arany.shg.feature_thrift.domain.usecase.ThriftUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLoanViewModel @Inject constructor(
    private val loanUseCases: LoanUseCases,
    private val memberUseCases: MemberUseCases,
    state: SavedStateHandle
): ViewModel() {

    private var committeeId: Int = state.get<Int>(Constants.NAV_ARG_COMMITTEE_ID)!!

    var members = MutableStateFlow<List<Member>>(arrayListOf())
        private set

    var loanAmount = MutableStateFlow(TextFieldState(hint = "Enter Loan Amount"))
        private set

    var member = MutableStateFlow(MemberState(hint = "Select Member"))
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

    fun onEvent(event: AddLoanEvent) {
        when(event){
            is AddLoanEvent.EnteredLoanAmount -> {
                loanAmount.value = loanAmount.value.copy(text = event.amount)
            }
            is AddLoanEvent.SelectedMember -> {
                member.value = member.value.copy(member = event.member)
            }
            is AddLoanEvent.AddLoan -> {
                viewModelScope.launch(Dispatchers.IO + addLoanExceptionHandler) {
                    if(null == member.value.member)
                        _eventFlow.emit(UiEvent.ShowError("Select member"))
                    else if(loanAmount.value.text.isEmpty())
                        _eventFlow.emit(UiEvent.ShowError("Enter loan amount"))
                    else{
                        loanUseCases.createLoanUseCase(Loan(loanId = null, committeeId = committeeId, memberId = member.value.member?.memberId, amount = loanAmount.value.text.toDoubleOrNull()?: 0.0, dateTime = DateUtils.getCurrentDateTime().toFormattedString()))
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