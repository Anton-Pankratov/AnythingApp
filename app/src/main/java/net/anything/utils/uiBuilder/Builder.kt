package net.anything.utils.uiBuilder

import android.view.View

interface Builder {

    fun View.setLayoutParams(width: Int, height: Int)
}