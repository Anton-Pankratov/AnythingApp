package net.anything.utils.uiBuilder.viewGenerator

import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import net.anything.domain.entity.ShowSign
import net.anything.utils.transactions.OnTransaction
import net.anything.ui.things.view.item.ThingItem
import net.anything.ui.things.view.recycler.ThingsView
import net.anything.utils.dbMode.OnChangeDbModeListener

interface ViewGenerator {

    /**
     * Main Activity
     */

    val fragmentContainer: FrameLayout

    /**
     * Root Layout for Fragments
     */

    val root: ConstraintLayout

    /**
     * Action Bar Settings
     */

    fun Menu.addFilter(listener: OnTransaction)

    fun Menu.addDbChange(listener: OnChangeDbModeListener)

    /**
     * Things Fragment
     */

    // Buttons

    val createNewThingButton: MaterialButton

    val deleteAllThingsButton: MaterialButton

    // Recycler View

    val thingsView: ThingsView

    // Item for Recycler View

    fun createThingItem(signs: List<ShowSign?>): ThingItem

    fun createThingHeader(id: Int?): TextView

    fun createThingSign(header: String?, value: String?): TextView?

    /**
     * Common
     */

    fun generateId(): Int

    fun TextView.setSignLayoutParams(width: Int, height: Int, margin: Int)

    /**
     * Create Thing Fragment
     */

    fun View.setLayoutParams(width: Int, height: Int)
}