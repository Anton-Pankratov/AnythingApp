package net.anything.utils.uiBuilder.screen

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentManager
import net.anything.utils.uiBuilder.Builder

interface ScreenBuilder : Builder {

    fun buildContentView(fragmentManager: FragmentManager): View

    fun ConstraintLayout.addToolbar(fragmentManager: FragmentManager)

    fun ConstraintLayout.addNewItemButton()

    fun ConstraintLayout.addThingsView()

    fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    )
}