/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: SettingFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/02/26 12.24
 */

package id.co.ikonsultan.approval.feature.home.ui

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.home.R
import id.co.ikonsultan.approval.feature.home.adapter.HomeMenuAdapter
import id.co.ikonsultan.approval.feature.home.contract.HomeEffect
import id.co.ikonsultan.approval.feature.home.contract.HomeEvent
import id.co.ikonsultan.approval.feature.home.contract.HomeState
import id.co.ikonsultan.approval.feature.home.databinding.FragmentHomeBinding
import id.co.ikonsultan.approval.feature.home.databinding.FragmentSettingBinding
import id.co.ikonsultan.approval.feature.home.presentation.HomeViewModel
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class SettingFragment :
    BaseFragment<FragmentSettingBinding, HomeState, HomeEvent, HomeEffect, HomeViewModel>(R.layout.fragment_setting) {

    override val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    override fun bindView(view: View) =
        FragmentSettingBinding.bind(view)

    override fun setupView() {

    }

    override fun renderState(state: HomeState) {

    }

    override fun handleEffect(effect: HomeEffect) {

    }
}