package com.arany.shg.feature_committee.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.Constants
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_committee.data.model.CommitteeWithDetails
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommitteeListingViewModel @Inject constructor(
    private val committeeUseCases: CommitteeUseCases
): ViewModel() {

    var committeeWithDetails = MutableStateFlow<List<CommitteeWithDetails>>(arrayListOf())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            when(val fetchedCommitteeWithDetails = committeeUseCases.getCommitteesWithDetailsUseCase(Constants.ShgId)) {
                is Resource.Success -> {
                    committeeWithDetails.value = fetchedCommitteeWithDetails.data?: arrayListOf()
                }
                else -> {
                    //TODO Handle the case later
                }
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowError(throwable.message.toString()))
        }
    }

    fun onEvent(event: CommitteeListingEvent) {
        when(event){
            is CommitteeListingEvent.CommitteeCardClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.NavigateToCommitteeDetailsScreen(event.committeeWithDetails))
                }
            }
            else ->{
                //TODO Nothing
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        data class NavigateToCommitteeDetailsScreen(val committeeWithDetails: CommitteeWithDetails): UiEvent()
    }
}