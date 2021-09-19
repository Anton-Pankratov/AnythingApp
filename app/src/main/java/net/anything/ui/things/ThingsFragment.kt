package net.anything.ui.things

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.anything.anythingapp.R
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.MainActivity
import net.anything.ui.filter.OnFilterPreferenceClickListener
import net.anything.ui.things.view.adapter.ThingSwipeHelper
import net.anything.utils.KEY_THING
import net.anything.utils.dbMode.*
import net.anything.utils.getActivity
import net.anything.utils.getMainActivity
import net.anything.utils.transactions.Screens

class ThingsFragment : Fragment() {

    private val viewModel: ThingsViewModel by viewModels()

    private val transactionsListener
        get() = (activity?.getActivity() as MainActivity)
            .transactionsListener

    private val root by lazy {
        viewModel.screenBuilder
            .buildThingsScreen(transactionsListener) {
                viewModel.deleteAllThings()
            }
    }

    private val placeholder
        get() = viewModel.screenBuilder.placeholder

    private val deleteAllThingsButton
        get() = viewModel.screenBuilder.deleteAllThingsButton

    private val thingsView by lazy {
        viewModel.screenBuilder.thingsView
    }

    val filterListener = OnFilterPreferenceClickListener { filter ->
        when (currentDbMode) {
            DatabaseMode.ROOM -> collectSortedThings(filter)
            DatabaseMode.NATIVE -> lifecycleScope.launch {
                submit(viewModel.sortedThingsSql(filter))
            }
        }
    }

    private val dbChangeModeListener = OnChangeDbModeListener { mode ->
        when (mode) {
            DatabaseMode.ROOM -> viewModel.thingsFlowRoom?.collectThings()
            DatabaseMode.NATIVE -> lifecycleScope.launch {
                submit(viewModel.thingsSql())
            }
        }
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
        menu.addOptions()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun collectAllThings() {
        viewModel.apply {
            thingsFlowRoom?.collectThings()
            listenEventForGetThingsFromNativeSql()
        }
    }

    private fun collectSortedThings(filter: String) {
        viewModel.sortedThingsFlowRoom(filter)?.collectThings()
    }

    private fun Flow<List<ShowThingEntity>>.collectThings() {
        lifecycleScope.launch {
            collect { things ->
                if (currentDbMode == DatabaseMode.ROOM)
                    submit(things)
            }
        }
    }

    private fun listenEventForGetThingsFromNativeSql() {
        lifecycleScope.launchWhenCreated {
            changeDbModeEvent.collect { isNativeSql ->
                if (isNativeSql) {
                    submit(viewModel.thingsSql())
                    _changeDbModeEvent.tryEmit(false)
                }
            }
        }
    }

    private fun submit(things: List<ShowThingEntity>) {
        thingsView.submit(things) {
            transactionsListener.begin(
                Screens.UPDATE_THING,
                bundleOf(KEY_THING to it)
            )
        }
        (root as ConstraintLayout).removeView(placeholder)
        if (!things.isNullOrEmpty()) {
            deleteAllThingsButton?.isVisible = true
        } else {
            viewModel.screenBuilder.apply {
                (root as ConstraintLayout).removeView(placeholder)
                (root as ConstraintLayout).addPlaceholder()
            }
        }
    }


    private fun setSwipeAction() {
        ThingSwipeHelper(viewModel::deleteThing)
            .attachToRecyclerView(thingsView)
    }

    private fun Menu.addOptions() {
        viewModel.screenBuilder.apply {
            this@addOptions.apply {
                addDbChangeOption(dbChangeModeListener)
                addFilterOption(transactionsListener)
            }
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