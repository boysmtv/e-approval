/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HistoryViewModel.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.44
 */

package id.co.ikonsultan.approval.feature.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import id.co.ikonsultan.approval.feature.home.contract.*
import id.co.ikonsultan.approval.feature.home.data.Ticket
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : BaseViewModel<HistoryState, HistoryEvent, HistoryEffect>(dispatcher) {

    override fun createInitialState() = HistoryState()

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect { event ->
                when (event) {
                    HistoryEvent.LoadData -> loadDummy()
                    HistoryEvent.OnBackClicked -> sendEffect { HistoryEffect.NavigateBack }
                    else -> {}
                }
            }
        }
    }

    private fun loadDummy() {
        setState {
            copy(
                tickets = listOf(
                    Ticket("Ticket 001", "30/10/2025", "Reject", "Group Head"),
                    Ticket("Ticket 002", "01/11/2025", "Approve", "Supervisor"),
                    Ticket("Ticket 003", "02/11/2025", "Escalate", "Manager"),
                    Ticket("Ticket 004", "03/11/2025", "Submited", "Director"),
                    Ticket("Ticket 005", "04/11/2025", "Draft", "Supervisor"),
                    Ticket("Ticket 006", "05/11/2025", "Approve", "Manager")
                )
            )
        }
    }
}