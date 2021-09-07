package net.anything.utils.uiBuilder.view

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import net.anything.utils.uiBuilder.Builder

interface ViewGenerator : Builder {

    val toolbar: MaterialToolbar

    val addNewItemButton: MaterialButton

    fun generateId(): Int

}