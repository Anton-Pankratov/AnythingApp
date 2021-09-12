package net.anything.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.anything.anythingapp.R

class CreateThingFragment : BottomSheetDialogFragment() {

    private val viewModel: CreateThingViewModel by viewModels()

    private val root by lazy {
        viewModel.screenBuilder.buildCreateThingDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_thing, container, false)
    }

    companion object {
        fun getInstance() = CreateThingFragment()
    }
}