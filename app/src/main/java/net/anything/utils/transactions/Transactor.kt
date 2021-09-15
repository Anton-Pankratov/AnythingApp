package net.anything.utils.transactions

import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface Transactor {

    fun FragmentManager.openScreen(fragment: Fragment, container: FrameLayout)

    fun FragmentManager.openDialog(fragment: BottomSheetDialogFragment)

    fun Fragment.makeTransaction(fm: FragmentManager, container: View)
}