package net.anything.utils.uiParams

import android.R
import android.content.Context
import android.widget.LinearLayout

val MatchParent = UiSizes.MATCH_PARENT.size
val WrapContent = UiSizes.WRAP_CONTENT.size

val Context.actionBarSize
    get() = theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        .let { attrs -> attrs.getDimension(0, 0F).toInt().also { attrs.recycle() } }

enum class UiSizes(val size: Int) {
    MATCH_PARENT(LinearLayout.LayoutParams.MATCH_PARENT),
    WRAP_CONTENT(LinearLayout.LayoutParams.WRAP_CONTENT),
}

