package com.example.hearthstonedecks.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hearthstonedecks.DeckApplication
import com.example.hearthstonedecks.databinding.DeckFragmentBinding

class DeckFragment : Fragment(), DeckAdapter.OnItemClickListener {
    private lateinit var binding: DeckFragmentBinding
    lateinit var adapter: DeckAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DeckFragmentBinding.inflate(inflater)

        val args : DeckFragmentArgs by navArgs()
        val deckId = args.id
        Log.d("___", "args: $args, deckId: $deckId")

        val viewModel: DeckViewModel by viewModels {
            DeckViewModelFactory(deckId, DeckApplication.repository)
        }

        adapter = DeckAdapter()
        adapter.setOnItemClickListener(this)

        binding.deckFragmentRecyclerView.adapter = adapter

        viewModel.deck.observe(viewLifecycleOwner, { deckDBItem ->
            Log.d("___","in fragment. deck = $deckDBItem")
            deckDBItem?.let { deck ->
                binding.deck = deck
                val listToSubmit = deck.cards.sortedBy {
                    it.manaCost
                }
                adapter.submitList(listToSubmit)
                Log.d("___","cards: ${deck.cards}")
            }
        })
        return binding.root
    }

    override fun onItemClick(position: Int) {
        val slug = adapter.currentList[position].slug
        slug?.let {
            val dialog = CardInfoDialog.newInstance(slug)
            dialog.show(activity?.supportFragmentManager!!, null)
        }

    }
}