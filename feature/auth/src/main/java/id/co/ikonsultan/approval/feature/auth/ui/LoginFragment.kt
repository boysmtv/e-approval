/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: LoginActivity.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.14
 */

package id.co.ikonsultan.approval.feature.auth.ui

import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.feature.auth.contract.LoginEffect
import id.co.ikonsultan.approval.feature.auth.contract.LoginEvent
import id.co.ikonsultan.approval.feature.auth.contract.LoginState
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.auth.R
import id.co.ikonsultan.approval.feature.auth.databinding.FragmentLoginBinding
import id.co.ikonsultan.approval.feature.auth.presentation.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginState, LoginEvent, LoginEffect, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    override fun bindView(view: View) =
        FragmentLoginBinding.bind(view)

    override fun setupView() {

        binding.etUsername.addTextChangedListener {
            viewModel.sendEvent(
                LoginEvent.OnEmailChanged(it.toString())
            )
        }

        binding.etPassword.addTextChangedListener {
            viewModel.sendEvent(
                LoginEvent.OnPasswordChanged(it.toString())
            )
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.sendEvent(LoginEvent.OnLoginClicked)
        }
    }

    override fun renderState(state: LoginState) {
        binding.btnSubmit.isEnabled = state.isButtonEnabled
    }

    override fun handleEffect(effect: LoginEffect) {
        when (effect) {
            is LoginEffect.NavigateHome -> navigator.openHome()
            is LoginEffect.ShowError -> Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
        }
    }
}