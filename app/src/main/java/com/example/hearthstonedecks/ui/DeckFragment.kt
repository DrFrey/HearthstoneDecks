package com.example.hearthstonedecks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hearthstonedecks.databinding.DeckFragmentBinding

class DeckFragment : Fragment() {
    lateinit var binding: DeckFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DeckFragmentBinding.inflate(inflater)



        return binding.root
    }
}