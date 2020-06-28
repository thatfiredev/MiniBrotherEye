package io.github.rosariopfernandes.minibrothereye.util

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager

class NonScrollableGridLayoutManager(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int,
    defStyleRes: Int
) : GridLayoutManager(context, attrs, defStyleAttr, defStyleRes) {

    override fun canScrollVertically() = false

    override fun canScrollHorizontally() = false
}