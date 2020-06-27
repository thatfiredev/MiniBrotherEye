package io.github.rosariopfernandes.minibrothereye.util

import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

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