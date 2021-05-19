package com.example.hearthstonedecks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstonedecks.data.Card
import com.example.hearthstonedecks.databinding.CardListItemBinding
import com.example.hearthstonedecks.ui.DeckAdapter.DeckViewHolder

class DeckAdapter : ListAdapter<Card, DeckViewHolder>(DeckComparator()) {
    class DeckViewHolder(val binding : CardListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.card = card
            binding.executePendingBindings()
        }
    }

    class DeckComparator : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding = CardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}