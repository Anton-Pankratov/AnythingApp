package net.anything.utils.transactions

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.anything.utils.TAG_CREATE_THING
import net.anything.utils.TAG_UPDATE_THING

class TransactorImpl : Transactor {

    override fun FragmentManager.openScreen(fragment: Fragment, container: FrameLayout) {
        fragment.makeTransaction(this, container)
    }

    override fun FragmentManager.openCreatingThingDialog(fragment: BottomSheetDialogFragment) {
        try {
            fragment.show(this, TAG_CREATE_THING)
        } catch (e: IllegalStateException) {}
    }

    override fun FragmentManager.openUpdatingThingDialog(fragment: BottomSheetDialogFragment) {
        try {
            fragment.show(this, TAG_UPDATE_THING)
        } catch (e: IllegalStateException) {}
    }

    override fun Fragment.makeTransaction(fm: FragmentManager, container: View) {
        fm.beginTransaction()
            .replace(container.id, this)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}