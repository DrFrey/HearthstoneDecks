package com.example.hearthstonedecks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstonedecks.data.DeckDBItem
import com.example.hearthstonedecks.databinding.DeckListItemBinding

class DeckListAdapter : ListAdapter<DeckDBItem, DeckListAdapter.DeckListViewHolder>(DeckComparator()) {
    class DeckListViewHolder(private val binding: DeckListItemBinding) : RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckListViewHolder {
        return DeckListViewHolder(DeckListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DeckListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}