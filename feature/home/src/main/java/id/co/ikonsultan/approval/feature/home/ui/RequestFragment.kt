/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: RequestFragment.kt
 *
 * Last modified by Dedy Wijaya on 25/02/26 09.11
 */

package id.co.ikonsultan.approval.feature.home.ui

import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.home.R
import id.co.ikonsultan.approval.feature.home.adapter.RequestAdapter
import id.co.ikonsultan.approval.feature.home.contract.RequestEffect
import id.co.ikonsultan.approval.feature.home.contract.RequestEvent
import id.co.ikonsultan.approval.feature.home.contract.RequestState
import id.co.ikonsultan.approval.feature.home.databinding.FragmentRequestBinding
import id.co.ikonsultan.approval.feature.home.presentation.RequestViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RequestFragment :
    BaseFragment<FragmentRequestBinding, RequestState, RequestEvent, RequestEffect, RequestViewModel>(R.layout.fragment_request) {

    override val viewModel: RequestViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    private val requestAdapter by lazy {
        RequestAdapter {
            viewModel.sendEvent(
                RequestEvent.OnTicketClicked(it.id)
            )
        }
    }

    override fun bindView(view: View) =
        FragmentRequestBinding.bind(view)

    override fun setupView() {
        binding.rvTicket.adapter = requestAdapter


        binding.header.setOnClickListener {
            navigator.back()
        }

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip =
                    binding.root.findViewById<Chip>(checkedIds.first())
                viewModel.sendEvent(
                    RequestEvent.OnFilterChanged(chip.text.toString())
                )
            }
        }

        viewModel.sendEvent(RequestEvent.LoadData)
    }

    override fun renderState(state: RequestState) {
        requestAdapter.submitList(state.tickets)
    }

    override fun handleEffect(effect: RequestEffect) {
        when (effect) {
            is RequestEffect.NavigateDetail ->
                navigator.openDetail(effect.id)

            RequestEffect.NavigateBack ->
                navigator.back()
        }
    }

}