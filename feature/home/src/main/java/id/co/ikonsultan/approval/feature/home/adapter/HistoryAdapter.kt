/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HistoryAdapter.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.44
 */

package id.co.ikonsultan.approval.feature.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.ikonsultan.approval.feature.home.data.Ticket
import id.co.ikonsultan.approval.feature.home.databinding.ItemListHistoryBinding

class HistoryAdapter(
    private val items: List<Ticket>
) : RecyclerView.Adapter<HistoryAdapter.VH>() {

    inner class VH(val binding: ItemListHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemListHistoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvDate.text = item.date
        holder.binding.tvStatus.text = item.status
        holder.binding.tvDepartment.text = item.department

        val color = when (item.status) {
            "Reject" -> Color.RED
            "Approve" -> Color.parseColor("#2979FF")
            "Escalate" -> Color.parseColor("#FF9800")
            "Submited" -> Color.parseColor("#00C853")
            "Draft" -> Color.parseColor("#FF5722")
            else -> Color.GRAY
        }

        holder.binding.tvStatus.setTextColor(color)
    }

    override fun getItemCount() = items.size
}