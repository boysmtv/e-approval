package id.co.ikonsultan.approval.feature.auth.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.feature.auth.contract.LoginEffect
import id.co.ikonsultan.approval.feature.auth.contract.LoginEvent
import id.co.ikonsultan.approval.feature.auth.contract.LoginState
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class LoginViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : BaseViewModel<LoginState, LoginEvent, LoginEffect>(dispatcher) {

    override fun createInitialState() = LoginState()

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect { event ->
                when (event) {
                    is LoginEvent.OnEmailChanged -> {
                        setState {
                            copy(
                                email = event.value,
                                isButtonEnabled = validate(event.value, password)
                            )
                        }
                    }

                    is LoginEvent.OnPasswordChanged -> {
                        setState {
                            copy(
                                password = event.value,
                                isButtonEnabled = validate(email, event.value)
                            )
                        }
                    }

                    is LoginEvent.OnLoginClicked -> login()
                }
            }
        }
    }

    private fun validate(email: String, password: String): Boolean {
        return email.isNotBlank() && password.length >= 6
    }

    private fun login() {
        setState { copy(isLoading = true) }
        launchIO {
            delay(1500)
            setState { copy(isLoading = false) }
            sendEffect { LoginEffect.NavigateHome }
        }
    }
}