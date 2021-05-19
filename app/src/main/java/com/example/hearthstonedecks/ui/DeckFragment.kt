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

class DeckFragment : Fragment() {
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
        binding.deckFragmentRecyclerView.adapter = adapter

        viewModel.deck.observe(viewLifecycleOwner, {
            Log.d("___","in fragment. deck = $it")
            it?.let {
                binding.deck = it
                adapter.submitList(it.cards)
                Log.d("___","cards: ${it.cards}")
            }
        })
        return binding.root
    }
}