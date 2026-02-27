/*
 * Project: E-Approval
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
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.ikonsultan.approval.core.common.base.BaseFragment
import id.co.ikonsultan.approval.core.common.navigation.AppNavigator
import id.co.ikonsultan.approval.feature.home.R
import id.co.ikonsultan.approval.core.ui.R as RUI
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
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = slaAdapter
        }

        binding.header.setOnClickListener {
            viewModel.sendEvent(DetailRequestEvent.OnBackClicked)
        }

        binding.btnBack.setOnClickListener {
            navigator.back()
        }

        binding.btnConfirm.setOnClickListener {
            showEscalateDialog(requireContext())
        }
    }

    override fun renderState(state: DetailRequestState) {
        val item = state.requestItem

        binding.tvTicketId.text = item.ticketId
        binding.etStaff.setText(item.staffName)
        binding.etReportType.setText(item.reportType)
        binding.etNotes.setText(item.notes)
        binding.etStatus.setText(item.status)
        binding.etFilesName.setText(item.fileName)

        slaAdapter.submitList(item.slaList.toList())

        binding.rvSla.post {
            binding.rvSla.requestLayout()
            binding.rvSla.invalidate()
        }    }

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
        dialog.setCancelable(true)

        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            setLayout(
                (context.resources.displayMetrics.widthPixels * 0.88).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            setGravity(Gravity.CENTER)

            attributes.windowAnimations = RUI.style.DialogAnimation
        }

        dialog.show()

        val adapter = EscalateAdapter {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            viewModel.sendEvent(DetailRequestEvent.OnConfirmClicked)
            dialog.dismiss()
        }

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = true
            this.adapter = adapter
        }

        adapter.submit(
            listOf(
                EscalateUser("Supervisor – John Stephani"),
                EscalateUser("Supervisor – John King"),
                EscalateUser("Supervisor – Andi Wijaya"),
                EscalateUser("Supervisor – Budi Santoso"),
                EscalateUser("Supervisor – Michael Tan"),

                EscalateUser("Dept Head – John Thor"),
                EscalateUser("Dept Head – Kevin Hartono"),
                EscalateUser("Dept Head – Rudi Setiawan"),
                EscalateUser("Dept Head – Samuel Lee"),
                EscalateUser("Dept Head – Andre Halim"),

                EscalateUser("Group Head – John Chris"),
                EscalateUser("Group Head – Denny Pratama"),
                EscalateUser("Group Head – Ricky Gunawan"),
                EscalateUser("Group Head – Hendry Lim"),
                EscalateUser("Group Head – David Kurniawan"),

                EscalateUser("Manager – Fajar Nugroho"),
                EscalateUser("Manager – Arif Hidayat"),
                EscalateUser("Manager – Donny Saputra"),
                EscalateUser("Manager – Steven Wijaya"),
                EscalateUser("Manager – Kevin Chandra"),

                EscalateUser("Director – Asep Komar", true),
                EscalateUser("Director – Bima Putra"),
                EscalateUser("Director – Calvin Setiawan"),
                EscalateUser("Director – Richard Tan"),
                EscalateUser("Director – Wilson Hartanto")
            )
        )

        binding.btnClose.setOnClickListener { dialog.dismiss() }

        binding.etSearch.addTextChangedListener { adapter.filter(it.toString()) }
    }

}