/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: SplashViewModel.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.22
 */

package id.co.ikonsultan.approval.feature.auth.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import id.co.ikonsultan.approval.feature.auth.contract.SplashEffect
import id.co.ikonsultan.approval.feature.auth.contract.SplashEvent
import id.co.ikonsultan.approval.feature.auth.contract.SplashState
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider
) : BaseViewModel<SplashState, SplashEvent, SplashEffect>(dispatcher) {

    override fun createInitialState() = SplashState()

    private var checked = false

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect { event ->
                when (event) {
                    SplashEvent.CheckSession -> checkSession()
                }
            }
        }
    }

    private fun checkSession() {
        if (checked) return
        checked = true

        launchIO {
            delay(2500)
            sendEffect { SplashEffect.NavigateLogin }
        }
    }
}