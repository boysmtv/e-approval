/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: SplashContract.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.22
 */

package id.co.ikonsultan.approval.feature.auth.contract

import id.co.ikonsultan.approval.core.common.base.UiEffect
import id.co.ikonsultan.approval.core.common.base.UiEvent
import id.co.ikonsultan.approval.core.common.base.UiState

data class SplashState(
    val isLoading: Boolean = true
) : UiState

sealed class SplashEvent : UiEvent {
    object CheckSession : SplashEvent()
}

sealed class SplashEffect : UiEffect {
    object NavigateLogin : SplashEffect()
}