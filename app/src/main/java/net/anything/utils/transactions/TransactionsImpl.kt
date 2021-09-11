package net.anything.utils.transactions

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import net.anything.ui.create.CreateThingFragment
import net.anything.ui.filter.FilterFragment

class TransactionsImpl : Transactions {

    override fun FragmentManager.openPreferencesScreen(container: ConstraintLayout) {
        beginTransaction()
            .replace(container.id, FilterFragment.getInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun FragmentManager.showCreatingNewThingScreen() {
        CreateThingFragment.getInstance().show(this, "new_thing")
    }
}