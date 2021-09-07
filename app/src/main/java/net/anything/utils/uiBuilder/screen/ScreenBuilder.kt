package net.anything.utils.uiBuilder.screen

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.utils.uiBuilder.Builder

interface ScreenBuilder : Builder {

    fun buildContentView(): View

    fun ConstraintLayout.addToolbar()

    fun ConstraintLayout.addNewItemButton()

    fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    )
}