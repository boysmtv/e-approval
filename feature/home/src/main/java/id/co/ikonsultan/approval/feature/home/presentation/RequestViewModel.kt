/*
 * Project: Boys.mtv@gmail.com
 * File: RequestViewModel.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.35
 */

package id.co.ikonsultan.approval.feature.home.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.ikonsultan.approval.core.common.base.BaseViewModel
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import id.co.ikonsultan.approval.feature.home.contract.RequestEffect
import id.co.ikonsultan.approval.feature.home.contract.RequestEvent
import id.co.ikonsultan.approval.feature.home.contract.RequestState
import id.co.ikonsultan.approval.feature.home.data.RequestItem
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    dispatcher: DispatcherProvider
) : BaseViewModel<RequestState, RequestEvent, RequestEffect>(dispatcher) {

    override fun createInitialState() = RequestState()

    init {
        observeEvent()
    }

    private fun observeEvent() {
        launchMain {
            event.collect {
                when (it) {
                    is RequestEvent.LoadData -> loadData()
                    is RequestEvent.OnFilterChanged -> filter(it.filter)
                    is RequestEvent.OnTicketClicked -> sendEffect { RequestEffect.NavigateDetail(it.id) }
                }
            }
        }
    }

    private fun loadData() {
        setState {
            copy(
                tickets = allTickets
            )
        }
    }

    private fun filter(filter: String) {
        val filtered = if (filter.equals("All", true)) {
            allTickets
        } else {
            allTickets.filter {
                it.department.equals(filter, ignoreCase = true)
            }
        }

        setState {
            copy(
                selectedFilter = filter,
                tickets = filtered
            )
        }
    }

    private val allTickets = listOf(

        RequestItem("1", "Ticket 001", "Pending", "2026-02-01", "Andi", "Budi", "Finance"),
        RequestItem("2", "Ticket 002", "Approved", "2026-02-02", "Siti", "Rina", "HR Team"),
        RequestItem("3", "Ticket 003", "Rejected", "2026-02-03", "Dewi", "Agus", "IT Team"),
        RequestItem("4", "Ticket 004", "Pending", "2026-02-04", "Rudi", "Budi", "Finance"),
        RequestItem("5", "Ticket 005", "Approved", "2026-02-05", "Tono", "Rina", "HR Team"),
        RequestItem("6", "Ticket 006", "Pending", "2026-02-06", "Lina", "Agus", "IT Team"),
        RequestItem("7", "Ticket 007", "Rejected", "2026-02-07", "Bima", "Budi", "GA Team"),
        RequestItem("8", "Ticket 008", "Approved", "2026-02-08", "Nina", "Rina", "Legal"),
        RequestItem("9", "Ticket 009", "Pending", "2026-02-09", "Raka", "Agus", "Finance"),
        RequestItem("10", "Ticket 010", "Approved", "2026-02-10", "Maya", "Budi", "HR Team"),

        RequestItem("11", "Ticket 011", "Rejected", "2026-02-11", "Dian", "Rina", "IT Team"),
        RequestItem("12", "Ticket 012", "Pending", "2026-02-12", "Fajar", "Agus", "GA Team"),
        RequestItem("13", "Ticket 013", "Approved", "2026-02-13", "Hana", "Budi", "Legal"),
        RequestItem("14", "Ticket 014", "Pending", "2026-02-14", "Iqbal", "Rina", "Finance"),
        RequestItem("15", "Ticket 015", "Rejected", "2026-02-15", "Joko", "Agus", "HR Team"),
        RequestItem("16", "Ticket 016", "Approved", "2026-02-16", "Kiki", "Budi", "IT Team"),
        RequestItem("17", "Ticket 017", "Pending", "2026-02-17", "Lukman", "Rina", "GA Team"),
        RequestItem("18", "Ticket 018", "Approved", "2026-02-18", "Mega", "Agus", "Legal"),
        RequestItem("19", "Ticket 019", "Rejected", "2026-02-19", "Nando", "Budi", "Finance"),
        RequestItem("20", "Ticket 020", "Pending", "2026-02-20", "Oki", "Rina", "HR Team"),

        RequestItem("21", "Ticket 021", "Approved", "2026-02-21", "Putri", "Agus", "IT Team"),
        RequestItem("22", "Ticket 022", "Pending", "2026-02-22", "Qori", "Budi", "GA Team"),
        RequestItem("23", "Ticket 023", "Rejected", "2026-02-23", "Rina", "Rina", "Legal"),
        RequestItem("24", "Ticket 024", "Approved", "2026-02-24", "Salsa", "Agus", "Finance"),
        RequestItem("25", "Ticket 025", "Pending", "2026-02-25", "Tari", "Budi", "HR Team"),
        RequestItem("26", "Ticket 026", "Approved", "2026-02-26", "Umar", "Rina", "IT Team"),
        RequestItem("27", "Ticket 027", "Rejected", "2026-02-27", "Vina", "Agus", "GA Team"),
        RequestItem("28", "Ticket 028", "Pending", "2026-02-28", "Wahyu", "Budi", "Legal"),
        RequestItem("29", "Ticket 029", "Approved", "2026-03-01", "Xena", "Rina", "Finance"),
        RequestItem("30", "Ticket 030", "Pending", "2026-03-02", "Yudi", "Agus", "HR Team")

    )
}