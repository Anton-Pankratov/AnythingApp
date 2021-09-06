package net.anything.utils

import android.content.Context
import androidx.annotation.StringRes

fun Context.getStringRes(@StringRes res: Int): String {
    return resources.getString(res)
}