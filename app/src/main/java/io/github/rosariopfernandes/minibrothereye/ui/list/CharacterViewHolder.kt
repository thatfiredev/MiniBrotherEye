package io.github.rosariopfernandes.minibrothereye.ui.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import io.github.rosariopfernandes.minibrothereye.R

class CharacterViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val ivPhoto: ImageView = v.findViewById(R.id.ivPhoto)
    private val tvName: TextView = v.findViewById(R.id.tvName)

    fun bindTo(item: CharacterListItem, clickListener: (CharacterListItem) -> Unit) {
        tvName.text = item.name
        ivPhoto.load(item.photoUrl)
        itemView.setOnClickListener {
            clickListener(item)
        }
    }
}