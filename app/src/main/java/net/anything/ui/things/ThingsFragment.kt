package net.anything.ui.things

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import net.anything.anythingapp.R
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.MainActivity
import net.anything.ui.things.view.adapter.ThingSwipeHelper
import net.anything.utils.getActivity
import net.anything.utils.getMainActivity

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
        setSwipeAction()
        collectThings()
    }

    override fun onResume() {
        super.onResume()
        activity?.configureActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.addFilterOption()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun collectThings() {
        viewModel.thingsFlow.onEach(::submitThings).launchIn(lifecycleScope)
    }

    private fun submitThings(things: List<ShowThingEntity>) {
        thingsView.submit(things)
    }

    private fun setSwipeAction() {
        ThingSwipeHelper(viewModel::deleteThing)
            .attachToRecyclerView(thingsView)
    }

    private fun Menu.addFilterOption() {
        viewModel.screenBuilder.apply {
            this@addFilterOption
                .addFilterOption(transactionsListener)
        }
    }

    private fun Activity.configureActionBar() {
        getMainActivity().supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            title = resources.getString(R.string.app_name)
        }
    }

    companion object {
        fun getInstance() = ThingsFragment()
    }
}