package net.anything.utils.uiBuilder.screen

import android.view.Menu
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentManager
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.uiBuilder.Builder

interface ScreenBuilder : Builder {

    /**
     * Fragment Container
     */

    fun buildFragmentContainer(): View

    /**
     * Action Bar
     */

    fun Menu.addFilterOption(listener: OnTransaction)

    /**
     * Things Fragment
     */

    fun buildThingsScreen(listener: OnTransaction): View

    fun ConstraintLayout.addNewItemButton(listener: OnTransaction)

    fun ConstraintLayout.addThingsView()

    fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    )

    /**
     * Filter Fragment
     */

    fun buildFilterScreen(): View
}