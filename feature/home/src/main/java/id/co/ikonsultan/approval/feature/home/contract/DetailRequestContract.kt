/*
 * Project: Boys.mtv@gmail.com
 * File: DetailRequestContract.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 15.09
 */

package id.co.ikonsultan.approval.feature.home.contract

import id.co.ikonsultan.approval.core.common.base.UiEffect
import id.co.ikonsultan.approval.core.common.base.UiEvent
import id.co.ikonsultan.approval.core.common.base.UiState

data class DetailRequestState(
    val ticketId: String = "",
    val reportType: String = "",
    val slaList: List<String> = emptyList()
) : UiState

sealed class DetailRequestEvent : UiEvent {
    data class LoadData(val id: String) : DetailRequestEvent()
    object OnBackClicked : DetailRequestEvent()
    object OnConfirmClicked : DetailRequestEvent()
}

sealed class DetailRequestEffect : UiEffect {
    object NavigateBack : DetailRequestEffect()
    object ShowSuccess : DetailRequestEffect()
}