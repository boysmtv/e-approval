/*
 * Project: Boys.mtv@gmail.com
 * File: RequestAdapter.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.39
 */

package id.co.ikonsultan.approval.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.co.ikonsultan.approval.feature.home.data.RequestItem
import id.co.ikonsultan.approval.feature.home.databinding.ItemListRequestBinding

class RequestAdapter(
    val onClick: (RequestItem) -> Unit
) : ListAdapter<RequestItem, RequestAdapter.ViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<RequestItem>() {
            override fun areItemsTheSame(
                oldItem: RequestItem,
                newItem: RequestItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RequestItem,
                newItem: RequestItem
            ) = oldItem == newItem
        }
    }

    inner class ViewHolder(
        private val binding: ItemListRequestBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RequestItem) {
            binding.tvTitle.text = item.title
            binding.tvStatus.text = item.status
            binding.tvDate.text = item.date
            binding.tvRequester.text = item.requester
            binding.tvApprover.text = item.approver

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemListRequestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}