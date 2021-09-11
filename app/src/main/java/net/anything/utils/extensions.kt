package net.anything.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import net.anything.anythingapp.R
import kotlin.math.roundToInt

fun Context.getStringRes(@StringRes res: Int): String {
    return resources.getString(res)
}

fun Context.toDp(value: Int): Int {
    return (value * resources.displayMetrics.density + 0.5f).roundToInt()
}

fun Context.getPrimaryColor(): Drawable? {
    return ContextCompat.getDrawable(this, R.color.design_default_color_primary_variant)
}