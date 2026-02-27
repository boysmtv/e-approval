/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: RequestFragment.kt
 *
 * Last modified by Dedy Wijaya on 25/02/26 09.11
 */

package id.co.ikonsultan.approval.feature.home.ui

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
import id.co.ikonsultan.approval.core.ui.R as RUI

@AndroidEntryPoint
class RequestFragment :
    BaseFragment<FragmentRequestBinding, RequestState, RequestEvent, RequestEffect, RequestViewModel>(R.layout.fragment_request) {

    override val viewModel: RequestViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    private var chipInitialized = false

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
        binding.rvTicket.layoutManager = LinearLayoutManager(requireContext())

        binding.rvTicket.adapter = requestAdapter

        binding.header.setOnClickListener {
            navigator.back()
        }

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip = binding.root.findViewById<Chip>(checkedIds.first())
                viewModel.sendEvent(
                    RequestEvent.OnFilterChanged(chip.text.toString())
                )
            }
        }

        viewModel.sendEvent(RequestEvent.LoadData)
    }

    override fun renderState(state: RequestState) {
        requestAdapter.submitList(state.tickets)

        if (!chipInitialized && state.tickets.isNotEmpty()) {
            val departments = state.tickets
                .map { it.department }
                .distinct()
                .sorted()

            setupChips(departments)
            chipInitialized = true
        }
    }

    override fun handleEffect(effect: RequestEffect) {
        when (effect) {
            is RequestEffect.NavigateDetail ->
                navigator.openCheckerDetail(effect.id)

            RequestEffect.NavigateBack ->
                navigator.back()
        }
    }

    private fun setupChips(departments: List<String>) {
        val chipGroup = binding.chipGroup
        chipGroup.removeAllViews()

        val inflater = layoutInflater

        departments.forEach { dept ->
            val chip = inflater.inflate(
                RUI.layout.item_filter_chip,
                chipGroup,
                false
            ) as Chip

            chip.text = dept
            chip.isCheckable = true
            chip.id = View.generateViewId()

            chipGroup.addView(chip)
        }

        if (chipGroup.childCount > 0) {
            val firstChip = chipGroup.getChildAt(0) as Chip
            firstChip.isChecked = true

            viewModel.sendEvent(
                RequestEvent.OnFilterChanged(firstChip.text.toString())
            )
        }

        chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip = group.findViewById<Chip>(checkedIds.first())
                viewModel.sendEvent(
                    RequestEvent.OnFilterChanged(chip.text.toString())
                )
            }
        }
    }

}