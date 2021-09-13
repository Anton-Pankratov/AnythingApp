package net.anything.utils.transactions

import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Transactor {

    fun FragmentManager.openThingsScreen(container: FrameLayout)

    fun FragmentManager.openPreferencesScreen(container: FrameLayout)

    fun FragmentManager.showCreatingNewThingScreen()

    fun Fragment.makeTransaction(fm: FragmentManager, container: View)
}