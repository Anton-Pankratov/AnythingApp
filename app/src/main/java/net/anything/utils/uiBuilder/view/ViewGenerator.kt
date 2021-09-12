package net.anything.utils.uiBuilder.view

import android.view.Menu
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentContainer
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.uiBuilder.Builder
import net.anything.utils.uiBuilder.list.ThingItem
import net.anything.utils.uiBuilder.list.ThingsView

interface ViewGenerator : Builder {

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

    fun createThingItem(signs: Map<String?, String?>): ThingItem

    fun createThingSign(header: String?, value: String?): TextView

    fun generateId(): Int

    fun TextView.setSignLayoutParams(width: Int, height: Int, margin: Int)

}