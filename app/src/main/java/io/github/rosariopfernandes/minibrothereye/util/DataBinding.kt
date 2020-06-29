package io.github.rosariopfernandes.minibrothereye.util

import android.text.TextUtils
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
import io.github.rosariopfernandes.minibrothereye.model.Work

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
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
            tvError.text = state.error.message
            tvError.isVisible = true
        }
        else -> tvError.isGone = true
    }
}

@BindingAdapter("imageSrc")
fun bindImageView(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.drawable.no_portrait)
    }
}

// @BindingAdapter("biography", "work", "connections")
fun bindCharacterBiography(
    // textView: TextView,
    biography: Biography,
    work: Work,
    connections: Connections
): String {
    var bioText = ""

    if (biography.fullName != "") {
        bioText += biography.fullName + " is a "
    }
    bioText += if (biography.alignment == "bad") {
        "Villain"
    } else {
        "Anti-hero"
    }
    if (biography.placeOfBirth != "-") {
        bioText += " born in " + biography.placeOfBirth
    }
    if (biography.alterEgos != "No alter egos found.") {
        bioText += ", also known as " + biography.alterEgos
    }
    bioText += "."
    if (biography.firstAppearance != "-") {
        bioText += " They first appeared in ${biography.firstAppearance}."
    }
    if (work.occupation != "-") {
        bioText += " They're known for working as ${work.occupation}."
    }
    if (work.base != "-") {
        bioText += " Usually working at ${work.base}."
    }
    if (connections.groupAffiliation != "-") {
        bioText += " They're affiliated with: " + connections.groupAffiliation + "."
    }
    if (connections.relatives != "-") {
        bioText += " Related to: " + connections.relatives + "."
    }
    if (biography.aliases[0] != "-") {
        bioText += " Some of their aliases include "
        bioText += TextUtils.join(", ", biography.aliases)
    }
    return bioText
    // textView.text = bioText
}