package net.anything.utils.transactions

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager

interface Transactions {

    fun FragmentManager.openPreferencesScreen(container: ConstraintLayout)

    fun FragmentManager.showCreatingNewThingScreen()
}