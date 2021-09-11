package net.anything.utils.uiBuilder.view

import android.view.Menu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import net.anything.utils.uiBuilder.Builder
import net.anything.utils.uiBuilder.list.ThingItem
import net.anything.utils.uiBuilder.list.ThingsView

interface ViewGenerator : Builder {

    val root: ConstraintLayout

    val toolbar: MaterialToolbar

    val addNewItemButton: MaterialButton

    val thingsView: ThingsView

    fun Menu.addFilter(listener: OnFilterClickListener)

    fun interface OnFilterClickListener { fun onClick() }

    fun createThingItem(signs: Map<String?, String?>): ThingItem

    fun createThingSign(header: String?, value: String?): TextView

    fun generateId(): Int

    fun TextView.setSignLayoutParams(width: Int, height: Int, margin: Int)

}