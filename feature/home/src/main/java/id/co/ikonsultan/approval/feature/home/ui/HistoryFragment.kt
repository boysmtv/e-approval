/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HistoryFragment.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 13.37
 */

package id.co.ikonsultan.approval.feature.home.ui

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.home.R
import id.co.ikonsultan.approval.feature.home.adapter.HistoryAdapter
import id.co.ikonsultan.approval.feature.home.contract.*
import id.co.ikonsultan.approval.feature.home.databinding.FragmentHistoryBinding
import id.co.ikonsultan.approval.feature.home.presentation.HistoryViewModel
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, HistoryState, HistoryEvent, HistoryEffect, HistoryViewModel>(R.layout.fragment_history) {

    override val viewModel: HistoryViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    override fun bindView(view: View) =
        FragmentHistoryBinding.bind(view)

    override fun setupView() {
        viewModel.sendEvent(HistoryEvent.LoadData)

        binding.header.setOnClickListener {
            viewModel.sendEvent(HistoryEvent.OnBackClicked)
        }
    }

    override fun renderState(state: HistoryState) {
        binding.rvTicket.adapter = HistoryAdapter(state.tickets)
    }

    override fun handleEffect(effect: HistoryEffect) {
        when (effect) {
            HistoryEffect.NavigateBack -> navigator.backToHome()
        }
    }
}