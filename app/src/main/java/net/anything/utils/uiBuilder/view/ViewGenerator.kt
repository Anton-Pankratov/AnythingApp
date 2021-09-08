package net.anything.utils.uiBuilder.view

import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import net.anything.utils.uiBuilder.Builder
import net.anything.utils.uiBuilder.list.ThingItem

interface ViewGenerator : Builder {

    val toolbar: MaterialToolbar

    val addNewItemButton: MaterialButton

    fun createThingItem(signs: Map<String?, String?>): ThingItem

    fun createThingSign(header: String?, value: String?): TextView

    fun generateId(): Int

}