/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HistoryContract.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.43
 */

package id.co.ikonsultan.approval.feature.home.contract

import id.co.ikonsultan.approval.core.common.base.UiEffect
import id.co.ikonsultan.approval.core.common.base.UiEvent
import id.co.ikonsultan.approval.core.common.base.UiState
import id.co.ikonsultan.approval.feature.home.data.Ticket

data class HistoryState(
    val tickets: List<Ticket> = emptyList()
) : UiState

sealed class HistoryEvent : UiEvent {
    object LoadData : HistoryEvent()
    data class OnTicketClicked(val ticket: Ticket) : HistoryEvent()
    object OnBackClicked : HistoryEvent()
}

sealed class HistoryEffect : UiEffect {
    object NavigateBack : HistoryEffect()
}