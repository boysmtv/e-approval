/*
 * Project: Shopme App
 * Author: Boys.mtv@gmail.com
 * File: ReqeustDetailFragment.kt
 *
 * Last modified by Dedy Wijaya on 25/02/26 09.38
 */

package id.co.ikonsultan.approval.feature.home.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.home.R
import id.co.ikonsultan.approval.feature.home.adapter.EscalateAdapter
import id.co.ikonsultan.approval.feature.home.adapter.SlaAdapter
import id.co.ikonsultan.approval.feature.home.contract.DetailRequestEffect
import id.co.ikonsultan.approval.feature.home.contract.DetailRequestEvent
import id.co.ikonsultan.approval.feature.home.contract.DetailRequestState
import id.co.ikonsultan.approval.feature.home.data.EscalateUser
import id.co.ikonsultan.approval.feature.home.databinding.DialogEscalateBinding
import id.co.ikonsultan.approval.feature.home.databinding.FragmentDetailRequestBinding
import id.co.ikonsultan.approval.feature.home.presentation.DetailRequestViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailRequestFragment :
    BaseFragment<FragmentDetailRequestBinding, DetailRequestState, DetailRequestEvent, DetailRequestEffect, DetailRequestViewModel>(
        R.layout.fragment_detail_request
    ) {

    override val viewModel: DetailRequestViewModel by viewModels()

    @Inject
    lateinit var navigator: AppNavigator

    private val slaAdapter = SlaAdapter()

    override fun bindView(view: View) =
        FragmentDetailRequestBinding.bind(view)

    override fun setupView() {

        val ticketId = arguments?.getString("id") ?: ""
        viewModel.sendEvent(DetailRequestEvent.LoadData(ticketId))

        binding.rvSla.apply {
            layoutManager =
                GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
            adapter = slaAdapter
        }

        binding.header.setOnClickListener {
            viewModel.sendEvent(DetailRequestEvent.OnBackClicked)
        }

        binding.btnConfirm.setOnClickListener {
            showEscalateDialog(requireContext())
        }
    }

    override fun renderState(state: DetailRequestState) {
        binding.etUsername.setText(state.reportType)
        slaAdapter.submitList(state.slaList)
    }

    override fun handleEffect(effect: DetailRequestEffect) {
        when (effect) {
            DetailRequestEffect.NavigateBack -> navigator.back()
            DetailRequestEffect.ShowSuccess -> Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun showEscalateDialog(context: Context) {
        val dialog = Dialog(context)
        val binding = DialogEscalateBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val adapter = EscalateAdapter {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            viewModel.sendEvent(DetailRequestEvent.OnConfirmClicked)
            dialog.dismiss()
        }

        binding.rvUser.layoutManager = LinearLayoutManager(context)
        binding.rvUser.adapter = adapter

        adapter.submit(
            listOf(
                EscalateUser("Supervisor – John Stephani"),
                EscalateUser("Dept Head – John Thor"),
                EscalateUser("Group Head – John Chris"),
                EscalateUser("Supervisor – John King"),
                EscalateUser("Director – Asep Komar", true)
            )
        )

        binding.btnClose.setOnClickListener { dialog.dismiss() }

        binding.etSearch.addTextChangedListener {
            adapter.filter(it.toString())
        }
    }
}