package net.anything.utils.transactions

import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.anything.ui.create.CreateThingFragment
import net.anything.ui.filter.FilterFragment
import net.anything.ui.things.ThingsFragment

class TransactorImpl : Transactor {

    override fun FragmentManager.openScreen(fragment: Fragment, container: FrameLayout) {
        fragment.makeTransaction(this, container)
    }

    override fun FragmentManager.openDialog(fragment: BottomSheetDialogFragment) {
        fragment.show(this, "create_thing")
    }

    override fun Fragment.makeTransaction(fm: FragmentManager, container: View) {
        fm.beginTransaction()
            .replace(container.id, this)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}