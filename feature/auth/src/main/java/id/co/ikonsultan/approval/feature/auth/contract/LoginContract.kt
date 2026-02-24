/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: LoginContract.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.31
 */

package id.co.ikonsultan.approval.feature.auth.contract

import id.co.ikonsultan.approval.core.common.base.UiEffect
import id.co.ikonsultan.approval.core.common.base.UiEvent
import id.co.ikonsultan.approval.core.common.base.UiState

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
) : UiState

sealed class LoginEvent : UiEvent {
    data class OnEmailChanged(val value: String) : LoginEvent()
    data class OnPasswordChanged(val value: String) : LoginEvent()
    object OnLoginClicked : LoginEvent()
}

sealed class LoginEffect : UiEffect {
    object NavigateHome : LoginEffect()
    data class ShowError(val message: String) : LoginEffect()
}