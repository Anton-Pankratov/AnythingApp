package net.anything.utils.uiBuilder.screenBuilder

import android.view.Menu
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.utils.transactions.OnTransaction
import net.anything.ui.things.view.ThingsView

interface ScreenBuilder {

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

    val thingsView: ThingsView

    fun buildThingsScreen(listener: OnTransaction): View

    fun ConstraintLayout.addNewItemButton(listener: OnTransaction)

    fun ConstraintLayout.addThingsView()

    fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    )

    /**
     * Create Thing Fragment
     */

    fun buildCreateThingDialog(): View
}