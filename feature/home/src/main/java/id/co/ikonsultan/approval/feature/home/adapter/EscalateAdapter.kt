/*
 * Project: Boys.mtv@gmail.com
 * File: EscalateAdapter.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 16.38
 */

package id.co.ikonsultan.approval.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.ikonsultan.approval.feature.home.data.EscalateUser
import id.co.ikonsultan.approval.feature.home.databinding.ItemEscalateUserBinding

class EscalateAdapter(
    private val onClick: (EscalateUser) -> Unit
) : RecyclerView.Adapter<EscalateAdapter.VH>() {

    private val fullList = mutableListOf<EscalateUser>()
    private val list = mutableListOf<EscalateUser>()

    fun submit(data: List<EscalateUser>) {
        fullList.clear()
        fullList.addAll(data)
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        list.clear()
        if (query.isEmpty()) {
            list.addAll(fullList)
        } else {
            list.addAll(fullList.filter {
                it.name.contains(query, true)
            })
        }
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemEscalateUserBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EscalateUser) {
            binding.tvName.text = item.name
            binding.ivCheck.visibility =
                if (item.isSelected) View.VISIBLE else View.GONE

            binding.root.setOnClickListener {
                fullList.forEach { it.isSelected = false }
                item.isSelected = true
                notifyDataSetChanged()
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemEscalateUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }
}