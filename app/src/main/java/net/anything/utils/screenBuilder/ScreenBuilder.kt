package net.anything.utils.screenBuilder

import android.view.View
import android.widget.LinearLayout

interface ScreenBuilder {

    fun buildContentView(): View

    fun LinearLayout.addToolbar()

    fun View.setLayoutParams(width: Int, height: Int)
}