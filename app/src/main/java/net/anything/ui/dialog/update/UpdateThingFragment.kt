package net.anything.ui.dialog.update

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.viewModels
import net.anything.anythingapp.R
import net.anything.domain.entity.ShowSign
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.dialog.base.BaseDialogFragment
import net.anything.utils.KEY_THING

class UpdateThingFragment : BaseDialogFragment() {

    override val viewModel: UpdateThingViewModel by viewModels()

    private val selectedThing by lazy {
        arguments?.getSerializable(KEY_THING) as ShowThingEntity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSelectedThingData()
        formActionButton(R.string.button_update_selected_thing) {
            viewModel.updateThing(getInputThing(selectedThing.id))
        }
    }

    private fun setSelectedThingData() {
        selectedThing.apply {
            binding?.apply {
                sign1?.setInput(header1, value1)
                sign2?.setInput(header2, value2)
                sign3?.setInput(header3, value3)
            }
        }
    }

    private fun ShowSign.setInput(headerField: EditText, valueField: EditText) {
        headerField.setText(header)
        valueField.setText(value)
    }

    companion object {
        fun instance() = UpdateThingFragment()
    }
}