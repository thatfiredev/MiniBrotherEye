package io.github.rosariopfernandes.minibrothereye.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.rosariopfernandes.minibrothereye.databinding.ItemCharacterBinding
import io.github.rosariopfernandes.minibrothereye.model.Character

class CharacterAdapter(
    private val clickListener: (CharacterListItem, Navigator.Extras) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
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

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(
            item: CharacterListItem,
            clickListener: (CharacterListItem, Navigator.Extras) -> Unit
        ) {
            binding.character = item
            ViewCompat.setTransitionName(binding.root, "${item.id}")
            itemView.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    binding.root to "${item.id}"
                )
                clickListener(item, extras)
            }
        }
    }

    /**
     * Helper class to represent the character displayed on the UI
     */
    data class CharacterListItem(val id: Int, val photoUrl: String, val name: String)

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