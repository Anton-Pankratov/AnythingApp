package net.anything.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.anything.anythingapp.R
import net.anything.anythingapp.databinding.DialogCreateThingBinding
import net.anything.domain.entity.ShowSign
import net.anything.domain.entity.ShowThingEntity

class CreateThingFragment : BottomSheetDialogFragment() {

    private val viewModel: CreateThingViewModel by viewModels()

    private var _binding: DialogCreateThingBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateThingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAddNewThingButtonClick()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun onAddNewThingButtonClick() {
        binding?.addThingBtn?.setOnClickListener {
            viewModel.saveNewThing(getNewThing())
        }
    }

    private fun getNewThing(): ShowThingEntity? {
        var entity: ShowThingEntity? = null
        binding?.apply {
            entity = ShowThingEntity(
                sign1 = ShowSign(header = header1.getInput(), value = value1.getInput()),
                sign2 = ShowSign(header = header2.getInput(), value = value2.getInput()),
                sign3 = ShowSign(header = header3.getInput(), value = value3.getInput()),
            )
        }
        return entity
    }

    private fun EditText.getInput() = text.toString()

    companion object {
        fun getInstance() = CreateThingFragment()
    }
}