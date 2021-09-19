package net.anything.utils.uiBuilder.screenBuilder

import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.button.MaterialButton
import net.anything.ui.things.view.recycler.ThingsView
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode.OnChangeDbModeListener
import net.anything.utils.transactions.OnTransaction

interface ScreenBuilder {

    /**
     * Fragment Container
     */

    fun buildFragmentContainer(): View

    /**
     * Action Bar
     */

    fun Menu.addFilterOption(listener: OnTransaction)

    fun Menu.addDbChangeOption(listener: OnChangeDbModeListener)

    /**
     * Things Fragment
     */

    val thingsView: ThingsView

    val placeholder: TextView?

    var deleteAllThingsButton: MaterialButton?

    fun buildThingsScreen(
        onTransactionListener: OnTransaction,
        onDeleteAllListener: OnDeleteAllThingsClickListener
    ): View

    fun ConstraintLayout.addNewThingButton(listener: OnTransaction)

    fun ConstraintLayout.addDeleteAllThingsButton(
        listener: OnDeleteAllThingsClickListener
    )

    fun interface OnDeleteAllThingsClickListener {
        fun onClick()
    }

    fun ConstraintLayout.addThingsView()

    fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    )

    fun ConstraintLayout.addPlaceholder()

    /**
     * Create Thing Fragment
     */

    fun buildCreateThingDialog(): View
}