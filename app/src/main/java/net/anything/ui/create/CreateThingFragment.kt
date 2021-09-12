package net.anything.ui.create

import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateThingFragment : BottomSheetDialogFragment() {

    private val viewModel: CreateThingViewModel by viewModels()

    companion object {
        fun getInstance() = CreateThingFragment()
    }
}