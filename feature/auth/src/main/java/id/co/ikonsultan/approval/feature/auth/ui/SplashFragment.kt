/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: SplashFragment.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 12.20
 */

package id.co.ikonsultan.approval.feature.auth.ui

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.auth.R
import id.co.ikonsultan.approval.feature.auth.contract.SplashEffect
import id.co.ikonsultan.approval.feature.auth.contract.SplashEvent
import id.co.ikonsultan.approval.feature.auth.contract.SplashState
import id.co.ikonsultan.approval.feature.auth.databinding.FragmentSplashBinding
import id.co.ikonsultan.approval.feature.auth.presentation.SplashViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashState, SplashEvent, SplashEffect, SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    override fun bindView(view: View) =
        FragmentSplashBinding.bind(view)

    override fun setupView() {
        viewModel.sendEvent(SplashEvent.CheckSession)
    }

    override fun renderState(state: SplashState) {
        // optional loading UI
    }

    override fun handleEffect(effect: SplashEffect) {
        when (effect) {
            SplashEffect.NavigateLogin -> navigator.openLogin()
        }
    }
}