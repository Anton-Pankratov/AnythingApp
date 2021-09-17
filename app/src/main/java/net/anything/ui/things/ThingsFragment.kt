package net.anything.ui.things

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.anything.anythingapp.R
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.MainActivity
import net.anything.ui.filter.OnFilterPreferenceClickListener
import net.anything.ui.things.view.adapter.ThingSwipeHelper
import net.anything.ui.things.view.item.OnThingClickListener
import net.anything.utils.KEY_THING
import net.anything.utils.getActivity
import net.anything.utils.getMainActivity
import net.anything.utils.transactions.Screens

class ThingsFragment : Fragment() {

    private val viewModel: ThingsViewModel by viewModels()

    private val transactionsListener
        get() = (activity?.getActivity() as MainActivity).transactionsListener

    private val root by lazy {
        viewModel.screenBuilder.buildThingsScreen(transactionsListener)
    }

    private val thingsView by lazy { viewModel.screenBuilder.thingsView }

    val filterListener = OnFilterPreferenceClickListener { filter ->
        collectSortedThings(filter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setSwipeAction()
        collectAllThings()
    }

    override fun onResume() {
        super.onResume()
        activity?.configureActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.addFilterOption()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun collectAllThings() {
        viewModel.thingsFlow.collectThings()
    }

    private fun collectSortedThings(filter: String) {
        viewModel.sortedThingsFlow(filter).collectThings()
    }

    private fun Flow<List<ShowThingEntity>>.collectThings() {
        lifecycleScope.launch {
            collect { things ->
                submitThings(things) {
                    transactionsListener.begin(
                        Screens.UPDATE_THING,
                        bundleOf(KEY_THING to it)
                    )
                }
            }
        }
    }

    private fun submitThings(
        things: List<ShowThingEntity>,
        listener: OnThingClickListener?
    ) = thingsView.submit(things, listener)

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