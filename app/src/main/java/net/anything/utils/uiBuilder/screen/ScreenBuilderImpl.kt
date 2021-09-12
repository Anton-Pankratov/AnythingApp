package net.anything.utils.uiBuilder.screen

import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.transactions.Screens
import net.anything.utils.uiBuilder.constraints.ConstraintsMaker
import net.anything.utils.uiBuilder.sizes.MatchParent
import net.anything.utils.uiBuilder.view.ViewGenerator

class ScreenBuilderImpl(
    private val viewGenerator: ViewGenerator,
    private val constraintsMaker: ConstraintsMaker,
) : ScreenBuilder {

    /**
     * Fragment Container
     * in [net.anything.ui.MainActivity]
     * and [net.anything.ui.filter.FilterFragment]
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

    /**
     * Things Fragment
     * in [net.anything.ui.things.ThingsFragment]
     */

    override fun buildThingsScreen(listener: OnTransaction): View {
        return viewGenerator.root.apply {
            setLayoutParams(MatchParent, MatchParent)
            addNewItemButton(listener)
            addThingsView()
        }
    }

    override fun ConstraintLayout.addNewItemButton(listener: OnTransaction) {
        with(constraintsMaker) {
            ConstraintSet().apply {
                viewGenerator.addNewItemButton.let { view ->
                    view.setOnClickListener {
                        listener.begin(Screens.NEW_THING)
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

    override fun ConstraintLayout.addThingsView() {
        with(constraintsMaker) {
            ConstraintSet().apply {
                viewGenerator.thingsView.let { view ->
                    add(view, this) {
                        connectToParentTop(view)
                        connectToParentStart(view)
                        connectToParentEnd(view)
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
     * Root Layout for Fragments
     * in [net.anything.ui.things.ThingsFragment]
     * and [net.anything.ui.filter.FilterFragment]
     */

    override fun buildFilterScreen(): View {
        return viewGenerator.root
    }

    override fun View.setLayoutParams(width: Int, height: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }
}