package com.example.hearthstonedecks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstonedecks.data.DeckDBItem
import com.example.hearthstonedecks.databinding.DeckFragmentBinding
import com.example.hearthstonedecks.ui.DeckAdapter.DeckViewHolder

class DeckAdapter : ListAdapter<DeckDBItem, DeckViewHolder>(DeckComparator()) {
    class DeckViewHolder(val binding : DeckFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding = DeckFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}