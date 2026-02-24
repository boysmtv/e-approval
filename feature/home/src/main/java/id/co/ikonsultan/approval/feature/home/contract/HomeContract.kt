/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HomeContract.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.37
 */

package id.co.ikonsultan.approval.feature.home.contract

import id.co.ikonsultan.approval.core.common.base.UiEffect
import id.co.ikonsultan.approval.core.common.base.UiEvent
import id.co.ikonsultan.approval.core.common.base.UiState
import id.co.ikonsultan.approval.feature.home.data.HomeMenu

data class HomeState(
    val username: String = "",
    val company: String = "",
    val role: String = "",
    val menu: List<HomeMenu> = emptyList()
) : UiState

sealed class HomeEvent : UiEvent {
    object LoadData : HomeEvent()
    data class OnMenuClicked(val menu: HomeMenu) : HomeEvent()
}

sealed class HomeEffect : UiEffect {
    object NavigateMaker : HomeEffect()
    object NavigateChecker : HomeEffect()
    object NavigateHistory : HomeEffect()
}