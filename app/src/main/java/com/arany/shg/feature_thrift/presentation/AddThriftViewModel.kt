package com.arany.shg.feature_thrift.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.CommitteeState
import com.arany.shg.core.util.MemberState
import com.arany.shg.core.util.TextFieldState
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import com.arany.shg.feature_shg.presentation.create_shg.SelfHelpGroupEvent
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddThriftViewModel @Inject constructor(
    private val useCases: MemberUseCases,
    private val selfHelpGroupUseCases: SelfHelpGroupUseCases,
): ViewModel() {

    private var committees: List<Committee> = arrayListOf()
    private var members: List<Member> = arrayListOf()

    private var _thriftAmount = mutableStateOf(TextFieldState(hint = "Enter Thrift Amount"))
    var thriftAmount: State<TextFieldState> = _thriftAmount

    private var _committee = mutableStateOf(CommitteeState(hint = "Select Committee"))
    var committee: State<CommitteeState> = _committee

    private var _member = mutableStateOf(MemberState(hint = "Select Member"))
    var member: State<MemberState> = _member

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable -> Log.e("Exc", throwable.message.toString()) }) {
            //selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(0,"S R R SHG", "Arany"))
            Log.e("SHG", selfHelpGroupUseCases.getSelfHelpGroupByIdUseCase.execute(0).data?.name?:"")
            selfHelpGroupUseCases.getAllSelfHelpGroupsUseCase.execute().collect{
                it.forEach {
                    Log.e("SHG",it.toString())
                }
            }

            for (i in 1..5) {
                val member =
                    Member(i+100, 1, "Aswin$i", "Add$i", "phn$i", "asw$1@gmail.com", "aaaaaaaa")
                useCases.createMemberUseCase(member)
            }

            useCases.getMembersByShgIdUseCase(1).collect {
                it.forEach {
                    Log.e("Members", it.toString())
                }
            }
        }
    }

    fun onEvent(event: AddThriftEvent) {
        when(event){
            is AddThriftEvent.EnteredThriftAmount -> {
                _thriftAmount.value = _thriftAmount.value.copy(text = event.amount)
            }
            is AddThriftEvent.SelectedCommittee -> {
                _committee.value = _committee.value.copy(committee = event.committee)
            }
            is AddThriftEvent.SelectedMember -> {
                _member.value = _member.value.copy(member = event.member)
            }
            is AddThriftEvent.AddThrift -> {
                Log.e("Login","ShgEvent.CreateSHG Triggered")
                viewModelScope.launch {
                    /*try {
                        selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(name = shgName.value.text, address = address.value.text))
                        _eventFlow.emit(UiEvent.CreateSelfHelpGroupVerified)
                    }catch (e: InvalidLoginException){
                        Log.e("Login","LoginEvent.VerifyLogin Exception")
                        _eventFlow.emit(UiEvent.ShowSnackBarError(e.message?:"Couldn't create Self Help Group"))
                    }*/
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object ThriftAdded: UiEvent()
    }
}