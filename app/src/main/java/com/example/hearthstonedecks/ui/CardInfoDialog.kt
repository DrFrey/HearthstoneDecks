package com.example.hearthstonedecks.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.hearthstonedecks.databinding.CardInfoDialogBinding

class CardInfoDialog : DialogFragment() {

    lateinit var binding: CardInfoDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CardInfoDialogBinding.inflate(LayoutInflater.from(context))

        val slug = arguments?.getString(SLUG) ?: ""

        val viewmodel : CardInfoDialogViewModel by viewModels {
            CardInfoDialogViewModelFactory(slug)
        }

        viewmodel.card.observe(this, {
            binding.card = it
        })
        binding.lifecycleOwner = this

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    companion object {
        const val SLUG = "SLUG"

        fun newInstance(slug: String) : CardInfoDialog {
            val dialog = CardInfoDialog()
            val args = Bundle()
            args.putString(SLUG, slug)
            dialog.arguments = args
            return dialog
        }
    }
}