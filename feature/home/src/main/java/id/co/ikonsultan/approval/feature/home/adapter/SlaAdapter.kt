/*
 * Project: Boys.mtv@gmail.com
 * File: SlaAdapter.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.50
 */

package id.co.ikonsultan.approval.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.ikonsultan.approval.feature.home.data.DetailRequestSlaItem
import id.co.ikonsultan.approval.feature.home.databinding.ItemListSlaBinding

class SlaAdapter :
    ListAdapter<DetailRequestSlaItem, SlaAdapter.ViewHolder>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<DetailRequestSlaItem>() {
            override fun areItemsTheSame(oldItem: DetailRequestSlaItem, newItem: DetailRequestSlaItem) =
                oldItem.role == newItem.role

            override fun areContentsTheSame(oldItem: DetailRequestSlaItem, newItem: DetailRequestSlaItem) =
                oldItem == newItem
        }
    }

    inner class ViewHolder(
        private val binding: ItemListSlaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DetailRequestSlaItem) {
            binding.tvTitle.text = item.role

             /*Glide.with(binding.imgAvatar)
                 .load(item.imageUrl)
                 .placeholder(R.drawable.ic_user)
                 .into(binding.imgAvatar)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListSlaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}