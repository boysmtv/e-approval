/*
 * Project: Boys.mtv@gmail.com
 * File: RequestViewModel.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.35
 */

package id.co.ikonsultan.approval.feature.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import id.co.ikonsultan.approval.feature.home.contract.RequestEffect
import id.co.ikonsultan.approval.feature.home.contract.RequestEvent
import id.co.ikonsultan.approval.feature.home.contract.RequestState
import id.co.ikonsultan.approval.feature.home.data.RequestItem
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class RequestViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : BaseViewModel<RequestState, RequestEvent, RequestEffect>(dispatcher) {

    override fun createInitialState() = RequestState()

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect {
                when (it) {
                    RequestEvent.LoadData -> loadData()
                    is RequestEvent.OnFilterChanged -> filter(it.filter)
                    is RequestEvent.OnTicketClicked -> sendEffect { RequestEffect.NavigateDetail(it.id) }
                }
            }
        }
    }

    private fun loadData() {
        setState {
            copy(
                tickets = listOf(
                    RequestItem("1", "Ticket 001", "Finance", "Pending"),
                    RequestItem("2", "Ticket 002", "HR", "Approved"),
                    RequestItem("3", "Ticket 003", "IT", "Rejected")
                )
            )
        }
    }

    private fun filter(filter: String) {
        setState { copy(selectedFilter = filter) }
    }
}