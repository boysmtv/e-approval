/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: HomeMenuAdapter.kt
 *
 * Last modified by Dedy Wijaya on 24/02/26 13.36
 */

package id.co.ikonsultan.approval.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.ikonsultan.approval.feature.home.data.HomeMenu
import id.co.ikonsultan.approval.feature.home.databinding.ItemMenuHomeBinding

class HomeMenuAdapter(
    private val items: List<HomeMenu>,
    private val onClick: (HomeMenu) -> Unit
) : RecyclerView.Adapter<HomeMenuAdapter.VH>() {

    inner class VH(val binding: ItemMenuHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemMenuHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.ivIcon.setImageResource(item.icon)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount() = items.size
}