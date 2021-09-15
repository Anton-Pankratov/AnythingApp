package net.anything.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import net.anything.anythingapp.R
import net.anything.ui.MainActivity
import kotlin.math.roundToInt

fun Activity.getMainActivity() =
    getActivity() as MainActivity

fun Context.getActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun Context.getStringRes(@StringRes res: Int): String {
    return resources.getString(res)
}

fun Context.toDp(value: Int): Int {
    return (value * resources.displayMetrics.density + 0.5f).roundToInt()
}

fun Context.getPrimaryColor(): Drawable? {
    return ContextCompat.getDrawable(this, R.color.design_default_color_primary_variant)
}