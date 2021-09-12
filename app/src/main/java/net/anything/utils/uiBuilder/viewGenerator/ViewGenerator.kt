package net.anything.utils.uiBuilder.viewGenerator

import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import net.anything.entity.ShowSign
import net.anything.utils.transactions.OnTransaction
import net.anything.ui.things.view.ThingItem
import net.anything.ui.things.view.ThingsView

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

    /**
     * Things Fragment
     */

    val addNewItemButton: MaterialButton

    val thingsView: ThingsView

    fun createThingItem(signs: List<ShowSign?>): ThingItem

    fun createThingHeader(id: Int): TextView

    fun createThingSign(header: String?, value: String?): TextView?

    fun generateId(): Int

    fun TextView.setSignLayoutParams(width: Int, height: Int, margin: Int)

    /**
     * Create Thing Fragment
     */


    fun View.setLayoutParams(width: Int, height: Int)

}