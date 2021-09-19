package net.anything.utils.transactions

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface Transactor {

    fun FragmentManager.openScreen(fragment: Fragment, container: FrameLayout)

    fun FragmentManager.openCreatingThingDialog(fragment: BottomSheetDialogFragment)

    fun FragmentManager.openUpdatingThingDialog(fragment: BottomSheetDialogFragment)

    fun Fragment.makeTransaction(fm: FragmentManager, container: View)
}