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
import id.co.ikonsultan.approval.feature.home.databinding.DialogEscalateItemBinding
import id.co.ikonsultan.approval.feature.home.databinding.ItemEscalateUserBinding

class EscalateAdapter(
    private val onClick: (EscalateUser) -> Unit
) : RecyclerView.Adapter<EscalateAdapter.ViewHolder>() {

    private val original = mutableListOf<EscalateUser>()
    private val filtered = mutableListOf<EscalateUser>()

    fun submit(list: List<EscalateUser>) {
        original.clear()
        original.addAll(list)

        filtered.clear()
        filtered.addAll(list)

        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val q = query.trim().lowercase()

        filtered.clear()
        if (q.isEmpty()) {
            filtered.addAll(original)
        } else {
            filtered.addAll(original.filter { it.name.lowercase().contains(q) })
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: DialogEscalateItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EscalateUser) {
            binding.tvTitle.text = item.name

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DialogEscalateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = filtered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filtered[position])
    }
}