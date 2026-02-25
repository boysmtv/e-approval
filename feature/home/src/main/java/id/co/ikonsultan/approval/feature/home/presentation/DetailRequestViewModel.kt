/*
 * Project: Boys.mtv@gmail.com
 * File: DetailRequestViewModel.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.49
 */

package id.co.ikonsultan.approval.feature.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import id.co.ikonsultan.approval.feature.home.contract.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DetailRequestViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : BaseViewModel<DetailRequestState, DetailRequestEvent, DetailRequestEffect>(dispatcher) {

    override fun createInitialState() = DetailRequestState()

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect { event ->
                when (event) {
                    is DetailRequestEvent.LoadData -> loadData(event.id)
                    DetailRequestEvent.OnBackClicked -> sendEffect { DetailRequestEffect.NavigateBack }
                    DetailRequestEvent.OnConfirmClicked -> {
                        sendEffect { DetailRequestEffect.ShowSuccess }
                        sendEffect { DetailRequestEffect.NavigateBack }
                    }
                }
            }
        }
    }

    private fun loadData(id: String) {
        setState {
            copy(
                ticketId = id,
                reportType = "Finance - 003",
                slaList = listOf("Maker", "Checker", "Signer")
            )
        }
    }
}