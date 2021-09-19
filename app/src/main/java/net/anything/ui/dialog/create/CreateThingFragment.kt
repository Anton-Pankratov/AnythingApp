package net.anything.ui.dialog.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import net.anything.anythingapp.R
import net.anything.ui.dialog.base.BaseDialogFragment

class CreateThingFragment : BaseDialogFragment() {

    override val viewModel: CreateThingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formActionButton(R.string.button_create_new_thing) {
            viewModel.saveNewThing(getInputThing())
        }
    }

    companion object {
        fun instance() = CreateThingFragment()
    }
}