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
                    Ticket("TK-001", "Payment approval", "Finance", "24 Feb 2026", "Pending"),
                    Ticket("TK-002", "Salary adjustment", "HR", "23 Feb 2026", "Approved"),
                    Ticket("TK-003", "Laptop request", "IT Team", "22 Feb 2026", "Rejected"),
                    Ticket("TK-004", "Budget revision", "Finance", "21 Feb 2026", "Pending"),
                    Ticket("TK-005", "Leave request", "HR", "20 Feb 2026", "Approved"),
                    Ticket("TK-006", "Server upgrade", "IT Team", "19 Feb 2026", "Pending"),
                    Ticket("TK-007", "Tax document", "Finance", "18 Feb 2026", "Approved"),
                    Ticket("TK-008", "Recruitment", "HR", "17 Feb 2026", "Pending"),
                    Ticket("TK-009", "VPN access", "IT Team", "16 Feb 2026", "Approved"),
                    Ticket("TK-010", "Expense claim", "Finance", "15 Feb 2026", "Rejected")
                )
            )
        }
    }
}