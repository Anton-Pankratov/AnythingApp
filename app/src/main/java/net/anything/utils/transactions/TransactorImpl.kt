package net.anything.utils.transactions

import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
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

    override fun FragmentManager.showCreatingNewThingScreen(container: FrameLayout) {
        CreateThingFragment.getInstance().makeTransaction(this, container)
    }

    override fun Fragment.makeTransaction(fm: FragmentManager, container: View) {
        fm.beginTransaction()
            .replace(container.id, this)
            .addToBackStack(null)
            .commit()
    }
}