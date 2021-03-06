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
    class DeckViewHolder(
        private val binding: CardListItemBinding,
        clickListener: OnItemClickListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardBaseLayout.setOnClickListener {
                if (clickListener != null) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(adapterPosition)
                    }
                }
            }
        }

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

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding =
            CardListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}