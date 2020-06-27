package io.github.rosariopfernandes.minibrothereye.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.model.Character

class CharacterAdapter(
    private val clickListener: (CharacterListItem) -> Unit
) : PagingDataAdapter<Character, CharacterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        character?.let {
            val characterItem = CharacterListItem(
                id = character.id,
                name = character.name,
                photoUrl = character.images.md
            )
            holder.bindTo(characterItem, clickListener)
        }
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ) = oldItem == newItem
        }
    }
}