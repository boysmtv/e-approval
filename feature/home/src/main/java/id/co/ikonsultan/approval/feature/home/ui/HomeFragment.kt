/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HomeFragment.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.36
 */

package id.co.ikonsultan.approval.feature.home.ui

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.home.R
import id.co.ikonsultan.approval.feature.home.adapter.HomeMenuAdapter
import id.co.ikonsultan.approval.feature.home.contract.*
import id.co.ikonsultan.approval.feature.home.databinding.FragmentHomeBinding
import id.co.ikonsultan.approval.feature.home.presentation.HomeViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeState, HomeEvent, HomeEffect, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    override fun bindView(view: View) =
        FragmentHomeBinding.bind(view)

    override fun setupView() {
        viewModel.sendEvent(HomeEvent.LoadData)
    }

    override fun renderState(state: HomeState) {
        binding.tvName.text = state.username
        binding.tvCompany.text = state.company
        binding.tvDepartment.text = state.role

        binding.rvMenu.adapter =
            HomeMenuAdapter(state.menu) {
                viewModel.sendEvent(HomeEvent.OnMenuClicked(it))
            }
    }

    override fun handleEffect(effect: HomeEffect) {
        when (effect) {
            HomeEffect.NavigateMaker -> navigator.openHistory()
            HomeEffect.NavigateChecker -> navigator.openChecker()
            HomeEffect.NavigateHistory -> {}
        }
    }
}