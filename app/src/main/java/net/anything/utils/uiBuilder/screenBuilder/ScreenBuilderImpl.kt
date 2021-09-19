package net.anything.utils.uiBuilder.screenBuilder

import android.view.Menu
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.ui.things.view.recycler.ThingsView
import net.anything.utils.dbMode.OnChangeDbModeListener
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.transactions.Screens
import net.anything.utils.uiBuilder.constraints.ConstraintsMaker
import net.anything.utils.uiBuilder.viewGenerator.ViewGenerator

class ScreenBuilderImpl(
    private val viewGenerator: ViewGenerator,
    private val constraintsMaker: ConstraintsMaker,
) : ScreenBuilder {

    /**
     * Fragment Container
     * in [net.anything.ui.MainActivity]
     * and [net.anything.ui.filter.FilterPreferenceFragment]
     */

    override fun buildFragmentContainer(): View {
        return viewGenerator.fragmentContainer
    }

    /**
     * Action Bar
     */

    override fun Menu.addFilterOption(listener: OnTransaction) {
        viewGenerator.apply {
            this@addFilterOption.addFilter(listener)
        }
    }

    override fun Menu.addDbChangeOption(listener: OnChangeDbModeListener) {
        viewGenerator.apply {
            this@addDbChangeOption.addDbChange(listener)
        }
    }

    /**
     * Things Fragment
     * in [net.anything.ui.things.ThingsFragment]
     */

    override val thingsView: ThingsView
        get() = viewGenerator.thingsView

    override fun buildThingsScreen(
        onTransactionListener: OnTransaction,
        onDeleteAllListener: ScreenBuilder.OnDeleteAllThingsClickListener
    ): View {
        return viewGenerator.root.apply {
            addNewThingButton(onTransactionListener)
            addDeleteAllThingsButton(onDeleteAllListener)
            addThingsView()
        }
    }

    override fun ConstraintLayout.addNewThingButton(listener: OnTransaction) {
        with(constraintsMaker) {
            ConstraintSet().apply {
                viewGenerator.createNewThingButton.let { view ->
                    view.setOnClickListener {
                        listener.begin(Screens.NEW_THING, null)
                    }
                    add(view, this) {
                        connectToParentStart(view)
                        connectToParentBottom(view)
                        connectToParentEnd(view)
                    }
                }
            }
        }
    }

    override fun ConstraintLayout.addDeleteAllThingsButton(
        listener: ScreenBuilder.OnDeleteAllThingsClickListener
    ) {
        with(constraintsMaker) {
            ConstraintSet().let { set ->
                viewGenerator.apply {
                    deleteAllThingsButton.let { deleteBtn ->
                        deleteBtn.setOnClickListener {
                            listener.onClick()
                        }
                        add(deleteBtn, set) {
                            set.connectToParentStart(deleteBtn)
                            set.connectToParentEnd(deleteBtn)
                            set.connectToBottomView(
                                deleteBtn, createNewThingButton
                            )
                        }
                    }
                }
            }
        }
    }

    override fun ConstraintLayout.addThingsView() {
        with(constraintsMaker) {
            ConstraintSet().let { set ->
                viewGenerator.apply {
                    thingsView.let { thingsView ->
                        add(thingsView, set) {
                            set.connectToParentTop(thingsView)
                            set.connectToParentStart(thingsView)
                            set.connectToParentEnd(thingsView)
                            set.connectToBottomView(
                                thingsView, deleteAllThingsButton
                            )
                        }
                    }
                }
            }
        }
    }

    override fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    ) {
        addView(view)
        with(constraintsMaker) {
            constraintSet.apply {
                setConstraints(this) {
                    connects.invoke()
                }
            }
        }
    }

    /**
     * Create Thing Fragment
     */

    override fun buildCreateThingDialog(): View {
        return viewGenerator.root
    }
}