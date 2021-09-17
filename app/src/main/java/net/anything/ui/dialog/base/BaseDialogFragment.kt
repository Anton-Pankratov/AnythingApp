package net.anything.ui.dialog.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import net.anything.anythingapp.databinding.DialogCreateUpdateThingBinding
import net.anything.domain.entity.ShowSign
import net.anything.domain.entity.ShowThingEntity

abstract class BaseDialogFragment : BottomSheetDialogFragment() {

    abstract val viewModel: BaseDialogViewModel

    private var _binding: DialogCreateUpdateThingBinding? = null
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateUpdateThingBinding
            .inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun formActionButton(
        @StringRes name: Int, action: () -> Unit
    ): MaterialButton? {
        return binding?.addThingBtn?.apply {
            setOnClickListener {
                lifecycleScope.launch { action() }
            }
            text = getString(name)
        }
    }

    protected fun getInputThing(
        thingId: Int? = null
    ): ShowThingEntity? {
        var entity: ShowThingEntity? = null
        binding?.apply {
            entity = ShowThingEntity(
                id = thingId,
                sign1 = ShowSign(
                    header = header1.getInput(),
                    value = value1.getInput()
                ),
                sign2 = ShowSign(
                    header = header2.getInput(),
                    value = value2.getInput()
                ),
                sign3 = ShowSign(
                    header = header3.getInput(),
                    value = value3.getInput()
                ),
            )
        }
        return entity
    }

    private fun EditText.getInput() = text.toString()
}