package com.example.hearthstonedecks.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.hearthstonedecks.DeckApplication
import com.example.hearthstonedecks.databinding.CardInfoDialogBinding
import kotlinx.coroutines.runBlocking

class CardInfoDialog(private val slug: String) : DialogFragment() {
    private val repository = DeckApplication.repository
    lateinit var binding: CardInfoDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CardInfoDialogBinding.inflate(LayoutInflater.from(context))
        binding.lifecycleOwner = this
        runBlocking {
            binding.card = repository.getCard(slug)
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
}