/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: BaseFragment.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.27
 */

package id.co.ikonsultan.approval.core.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<
        VB : ViewBinding,
        STATE : UiState,
        EVENT : UiEvent,
        EFFECT : UiEffect,
        VM : BaseViewModel<STATE, EVENT, EFFECT>
        >(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes) {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract val viewModel: VM
    abstract fun bindView(view: View): VB

    abstract fun renderState(state: STATE)
    open fun handleEffect(effect: EFFECT) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = bindView(view)

        observeState()
        observeEffect()

        setupView()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    renderState(it)
                }
            }
        }
    }

    private fun observeEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    handleEffect(it)
                }
            }
        }
    }

    open fun setupView() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}