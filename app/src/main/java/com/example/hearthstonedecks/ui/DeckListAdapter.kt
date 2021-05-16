package com.example.hearthstonedecks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstonedecks.data.DeckDBItem
import com.example.hearthstonedecks.databinding.DeckListItemBinding

class DeckListAdapter :
    ListAdapter<DeckDBItem, DeckListAdapter.DeckListViewHolder>(DeckComparator()) {
    class DeckListViewHolder(
        private val binding: DeckListItemBinding,
        clickListener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.baseLayout.setOnClickListener {
                if (clickListener != null) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(adapterPosition)
                    }
                }
            }

        }

        fun bind(deck: DeckDBItem) {
            binding.deck = deck
            binding.executePendingBindings()
        }
    }

    class DeckComparator : DiffUtil.ItemCallback<DeckDBItem>() {
        override fun areItemsTheSame(oldItem: DeckDBItem, newItem: DeckDBItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DeckDBItem, newItem: DeckDBItem): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckListViewHolder {
        val binding =
            DeckListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckListViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: DeckListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}