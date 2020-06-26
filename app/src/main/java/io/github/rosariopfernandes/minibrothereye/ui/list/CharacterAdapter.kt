package io.github.rosariopfernandes.minibrothereye.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.rosariopfernandes.minibrothereye.R

class CharacterAdapter(
    private val clickListener: (CharacterListItem) -> Unit
) : ListAdapter<CharacterListItem, CharacterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bindTo(character, clickListener)
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<CharacterListItem>() {
            override fun areItemsTheSame(
                oldItem: CharacterListItem,
                newItem: CharacterListItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterListItem,
                newItem: CharacterListItem
            ) = oldItem == newItem
        }
    }
}