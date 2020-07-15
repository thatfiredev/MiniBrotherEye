package io.github.rosariopfernandes.minibrothereye.util

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import io.github.rosariopfernandes.minibrothereye.R
import io.github.rosariopfernandes.minibrothereye.model.Biography
import io.github.rosariopfernandes.minibrothereye.model.Connections
import io.github.rosariopfernandes.minibrothereye.model.PowerStats
import io.github.rosariopfernandes.minibrothereye.model.Work
import io.github.rosariopfernandes.minibrothereye.ui.characterinfo.PowerStatAdapter

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("powerStats")
fun bindRecyclerViewAdapter(view: RecyclerView, powerStats: PowerStats) {
    view.adapter = PowerStatAdapter(powerStats)
}

@BindingAdapter("visibility")
fun bindRecyclerViewVisibility(recyclerView: RecyclerView, state: LoadState) {
    when (state) {
        is LoadState.Loading -> recyclerView.isGone = true
        is LoadState.Error -> recyclerView.isGone = true
        else -> recyclerView.isVisible = true
    }
}

@BindingAdapter("visibility")
fun bindProgressBarVisibility(progressBar: ProgressBar, state: LoadState) {
    when (state) {
        is LoadState.Loading -> progressBar.isVisible = true
        is LoadState.Error -> progressBar.isGone = true
        else -> progressBar.isGone = true
    }
}

@BindingAdapter("visibility")
fun bindErrorTextViewVisibility(tvError: TextView, state: LoadState) {
    when (state) {
        is LoadState.Loading -> tvError.isGone = true
        is LoadState.Error -> {
            tvError.setText(R.string.error_cant_load_data)
            state.error.printStackTrace()
            tvError.isVisible = true
        }
        else -> tvError.isGone = true
    }
}

@BindingAdapter("isVisibile")
fun bindVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageSrc")
fun bindImageView(imageView: ImageView, url: String?) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.drawable.no_portrait)
        error(R.drawable.no_portrait)
    }
}

@BindingAdapter("biography", "work", "connections")
fun bindCharacterBiography(
    textView: TextView,
    biography: Biography,
    work: Work,
    connections: Connections
) {
    val context = textView.context
    var bioText = ""

    if (biography.fullName != "") {
        bioText += context.getString(R.string.label_bio_name, biography.fullName)
    }
    bioText += if (biography.alignment == "bad") {
        context.getString(R.string.label_villain)
    } else {
        context.getString(R.string.label_antihero)
    }
    if (biography.placeOfBirth != "-") {
        bioText += context.getString(R.string.label_bio_place_of_birth, biography.placeOfBirth)
    }
    if (biography.alterEgos != "No alter egos found.") {
        bioText += context.getString(R.string.label_bio_alteregos, biography.alterEgos)
    }
    bioText += "."
    if (biography.firstAppearance != "-") {
        bioText += context.getString(R.string.label_bio_firstAppearance, biography.firstAppearance)
    }
    if (work.occupation != "-") {
        bioText += context.getString(R.string.label_bio_occupation, work.occupation)
    }
    if (work.base != "-") {
        bioText += context.getString(R.string.label_bio_work_base, work.base)
    }
    if (connections.groupAffiliation != "-") {
        bioText += context.getString(R.string.label_bio_affiliation, connections.groupAffiliation)
    }
    if (connections.relatives != "-") {
        bioText += context.getString(R.string.label_bio_relatives, connections.relatives)
    }
    if (biography.aliases.isNotEmpty() && biography.aliases[0] != "-") {
        bioText += context.getString(R.string.label_bio_aliases, TextUtils.join(", ", biography.aliases))
    }
    textView.text = bioText
}