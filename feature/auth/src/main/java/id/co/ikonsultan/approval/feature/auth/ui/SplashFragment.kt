/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: SplashFragment.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 12.20
 */

package id.co.ikonsultan.approval.feature.auth.ui

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.feature.auth.R
import id.co.ikonsultan.approval.feature.auth.contract.LoginEffect
import id.co.ikonsultan.approval.feature.auth.contract.LoginEvent
import id.co.ikonsultan.approval.feature.auth.contract.LoginState
import id.co.ikonsultan.approval.feature.auth.databinding.FragmentLoginBinding
import id.co.ikonsultan.approval.feature.auth.presentation.LoginViewModel

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<FragmentLoginBinding, LoginState, LoginEvent, LoginEffect, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel
        get() = TODO("Not yet implemented")

    override fun bindView(view: View): FragmentLoginBinding {
        TODO("Not yet implemented")
    }

    override fun renderState(state: LoginState) {
        TODO("Not yet implemented")
    }

}