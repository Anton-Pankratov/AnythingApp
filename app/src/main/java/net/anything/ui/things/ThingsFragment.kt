package net.anything.ui.things

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.anything.ui.MainActivity
import net.anything.utils.getActivity

class ThingsFragment : Fragment() {

    private val viewModel: ThingsViewModel by viewModels()

    private val transactionsListener
        get() = (activity?.getActivity() as MainActivity).transactionsListener

    private val root by lazy {
        viewModel.screenBuilder.buildThingsScreen(transactionsListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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