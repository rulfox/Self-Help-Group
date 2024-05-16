package com.arany.shg.feature_thrift.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.*
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.toFormattedString
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_committee.data.model.CommitteeWithDetails
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
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
class ThriftListingViewModel @Inject constructor(
    private val committeeUseCases: CommitteeUseCases,
    state: SavedStateHandle
): ViewModel() {

    var committeesWithDetails = MutableStateFlow<List<CommitteeWithDetails>>(arrayListOf())
        private set

    var thriftAddedAlertDialog = MutableStateFlow(AlertDialogState(title = "Thrift Added", description = "Your thrift amount has been updated. You can check your profile to know your thrift updated amount", isShown = false))
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val viewThriftsExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowError(throwable.message?:"Couldn't add Thrift"))
        }
    }

    init {
        viewModelScope.launch(viewThriftsExceptionHandler) {
            getCommitteesWithThriftDetails()
        }
    }

    private suspend fun getCommitteesWithThriftDetails(){
        when(val committeesWithDetailsInfo = committeeUseCases.getCommitteesWithDetailsUseCase(Constants.ShgId)){
            is Resource.Success -> {
                committeesWithDetails.value = committeesWithDetailsInfo.data ?: arrayListOf()
            }
            is Resource.Error -> {
                committeesWithDetails.value = arrayListOf()
            }
            is Resource.Loading -> {

            }
        }
    }
    fun onEvent(event: ViewThriftEvent) {
        when(event){
            is ViewThriftEvent.RefreshThrifts -> {
                viewModelScope.launch(viewThriftsExceptionHandler) {
                    getCommitteesWithThriftDetails()
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object ThriftAdded: UiEvent()
    }
}