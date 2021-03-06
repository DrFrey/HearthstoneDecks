package com.example.hearthstonedecks.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstonedecks.DeckApplication
import com.example.hearthstonedecks.R
import com.example.hearthstonedecks.databinding.DeckListFragmentBinding

class DeckListFragment : Fragment(), DeckListAdapter.OnItemClickListener {

    val viewModel : DeckListViewModel by viewModels {
        DeckListViewModelFactory(DeckApplication.repository)
    }

    lateinit var binding : DeckListFragmentBinding
    lateinit var adapter : DeckListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DeckListFragmentBinding.inflate(inflater)
        adapter = DeckListAdapter()
        binding.deckListRecyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            showAddDeckDialog()
        }

        viewModel.allDecks.observe(viewLifecycleOwner, { decks ->
            decks.let {
                adapter.submitList(decks)
            }
        })

        viewModel.errorReceived.observe(viewLifecycleOwner, {
            if (it) {
                showErrorDialog(viewModel.errorMessage.value.toString())
            }
        })

        //swipe helper
        val swipeHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val item = adapter.currentList[position]
                        viewModel.deleteDeck(item)
                    }
                }

            }
        )

        swipeHelper.attachToRecyclerView(binding.deckListRecyclerView)

        adapter.setOnItemClickListener(this)

        return binding.root
    }

    private fun showErrorDialog(value : String) {
        val createDialog = layoutInflater.inflate(R.layout.dialog_error, null)
        val text = createDialog.findViewById<TextView>(R.id.error_message)
        text.text = value
        AlertDialog.Builder(context)
            .setTitle(R.string.error_dialog_title)
            .setView(createDialog)
            .setNegativeButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showAddDeckDialog() {
        val createDialog = layoutInflater.inflate(R.layout.dialog_add, null)
        val text = createDialog.findViewById<EditText>(R.id.dialog_add)
        AlertDialog.Builder(context)
            .setTitle(R.string.add_deck_dialog_title)
            .setView(createDialog)
            .setPositiveButton(R.string.add_deck_button) { dialog, _ ->
                val code = text.text.toString()
                if (code.isNotEmpty()) {
                    viewModel.importDeck(code)
                    dialog.dismiss()
                }
            }
            .setNegativeButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onItemClick(position: Int) {
        Log.d("___", "position : $position")
        val id = adapter.currentList[position].id
        this.findNavController().navigate(DeckListFragmentDirections.actionMainScreenToDeckFragment(id))
    }
}