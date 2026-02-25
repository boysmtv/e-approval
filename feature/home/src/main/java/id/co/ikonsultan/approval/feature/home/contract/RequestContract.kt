/*
 * Project: Boys.mtv@gmail.com
 * File: RequestContract.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.35
 */

package id.co.ikonsultan.approval.feature.home.contract

import id.co.ikonsultan.approval.core.common.base.UiEffect
import id.co.ikonsultan.approval.core.common.base.UiEvent
import id.co.ikonsultan.approval.core.common.base.UiState
import id.co.ikonsultan.approval.feature.home.data.RequestItem

data class RequestState(
    val isLoading: Boolean = false,
    val selectedFilter: String = "Finance",
    val tickets: List<RequestItem> = emptyList()
) : UiState

sealed class RequestEvent : UiEvent {
    object LoadData : RequestEvent()
    data class OnFilterChanged(val filter: String) : RequestEvent()
    data class OnTicketClicked(val id: String) : RequestEvent()
}

sealed class RequestEffect : UiEffect {
    object NavigateBack : RequestEffect()
    data class NavigateDetail(val id: String) : RequestEffect()
}