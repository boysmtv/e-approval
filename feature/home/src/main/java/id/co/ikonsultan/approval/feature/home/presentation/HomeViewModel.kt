/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HomeViewModel.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.37
 */

package id.co.ikonsultan.approval.feature.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import id.co.ikonsultan.approval.core.ui.R
import id.co.ikonsultan.approval.feature.home.contract.*
import id.co.ikonsultan.approval.feature.home.data.HomeMenu
import id.co.ikonsultan.approval.feature.home.data.MenuType
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>(dispatcher) {

    override fun createInitialState() = HomeState()

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect { event ->
                when (event) {
                    is HomeEvent.LoadData -> loadData()
                    is HomeEvent.OnMenuClicked -> handleMenu(event.menu)
                }
            }
        }
    }

    private fun loadData() {
        setState {
            copy(
                username = "Roy Simotangkar",
                company = "PT. Gudang Garam Micin",
                role = "Director 12345",
                menu = listOf(
                    HomeMenu("Maker", R.drawable.ic_maker, MenuType.MAKER),
                    HomeMenu("Checker", R.drawable.ic_checker, MenuType.CHECKER),
                    HomeMenu("History", R.drawable.ic_history, MenuType.HISTORY)
                )
            )
        }
    }

    private fun handleMenu(menu: HomeMenu) {
        when (menu.type) {
            MenuType.MAKER -> sendEffect { HomeEffect.NavigateMaker }
            MenuType.CHECKER -> sendEffect { HomeEffect.NavigateChecker }
            MenuType.HISTORY -> sendEffect { HomeEffect.NavigateHistory }
        }
    }
}