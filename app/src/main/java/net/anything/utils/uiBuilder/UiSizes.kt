package net.anything.utils.uiBuilder

import android.content.Context
import android.widget.LinearLayout
import net.anything.anythingapp.R
import net.anything.utils.toDp

val MatchParent = UiSizes.MATCH_PARENT.size
val WrapContent = UiSizes.WRAP_CONTENT.size

fun Context.size6dp() = toDp(6)
fun Context.size10dp() = toDp(10)
fun Context.size16dp() = toDp(16)

val Context.actionBarSize
    get() = theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
        .let { attrs ->
            attrs.getDimension(0, 0F).toInt()
                .also { attrs.recycle() }
        }

enum class UiSizes(val size: Int) {
    MATCH_PARENT(LinearLayout.LayoutParams.MATCH_PARENT),
    WRAP_CONTENT(LinearLayout.LayoutParams.WRAP_CONTENT),
}

