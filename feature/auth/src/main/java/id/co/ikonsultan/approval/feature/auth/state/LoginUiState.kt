/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: LoginUiState.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.17
 */

package id.co.ikonsultan.approval.feature.auth.state

import id.co.ikonsultan.approval.core.domain.model.User

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: User) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}