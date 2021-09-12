package net.anything.ui.things

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import net.anything.entity.ShowSign
import net.anything.entity.ShowThingEntity
import net.anything.ui.MainActivity
import net.anything.utils.getActivity

class ThingsFragment : Fragment() {

    private val viewModel: ThingsViewModel by viewModels()

    private val transactionsListener
        get() = (activity?.getActivity() as MainActivity).transactionsListener

    private val root by lazy {
        viewModel.screenBuilder.buildThingsScreen(transactionsListener)
    }

    private val thingsView by lazy { viewModel.screenBuilder.thingsView }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        thingsView.submit(
            listOf(
                ShowThingEntity(
                    id = 1,
                    sign1 = ShowSign(1, "aaa", "bbb"),
                    sign2 = ShowSign(2, "aaa", "bbb"),
                    sign3 = ShowSign(3, "aaa", "bbb"),
                ),
                ShowThingEntity(
                    id = 2,
                    sign1 = ShowSign(1, "ccc", "bbb")
                ),
                ShowThingEntity(
                    id = 3,
                    sign2 = ShowSign(1, "ccc", "bbb")
                )
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.addFilterOption()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun Menu.addFilterOption() {
        viewModel.screenBuilder.apply {
            this@addFilterOption
                .addFilterOption(transactionsListener)
        }
    }

    companion object {
        fun getInstance() = ThingsFragment()
    }
}