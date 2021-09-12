package net.anything.utils.transactions

import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import net.anything.ui.create.CreateThingFragment
import net.anything.ui.filter.FilterFragment
import net.anything.ui.things.ThingsFragment

class TransactorImpl : Transactor {

    override fun FragmentManager.openThingsScreen(container: FrameLayout) {
        ThingsFragment.getInstance().makeTransaction(this, container)
    }

    override fun FragmentManager.openPreferencesScreen(container: FrameLayout) {
        FilterFragment.getInstance().makeTransaction(this, container)
    }

    override fun FragmentManager.showCreatingNewThingDialog() {
        CreateThingFragment.getInstance().show(this, "new_thing")
    }

    override fun Fragment.makeTransaction(fm: FragmentManager, container: FrameLayout) {
        fm.beginTransaction()
            .replace(container.id, this)
            .addToBackStack(null)
            .commit()
    }
}