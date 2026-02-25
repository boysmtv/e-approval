/*
 * Project: Boys.mtv@gmail.com
 * File: StatusAdapter.kt
 *
 * Last modified by Dedy Wijaya on 25/02/2026 14.32
 */

package id.co.ikonsultan.approval.feature.home.adapter

class StatusAdapter(
    private val items: List<StatusItem>,
    private val onClick: (StatusItem) -> Unit
) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemStatusBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StatusItem) {
            binding.tvTitle.text = item.title
            binding.tvTitle.setTextColor(item.color)

            val drawable = binding.viewIndicator.background.mutate()
            drawable.setTint(item.color)

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStatusBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}