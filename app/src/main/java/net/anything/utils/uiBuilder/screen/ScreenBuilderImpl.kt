package net.anything.utils.uiBuilder.screen

import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentManager
import net.anything.utils.transactions.Transactions
import net.anything.utils.uiBuilder.constraints.ConstraintsMaker
import net.anything.utils.uiBuilder.sizes.MatchParent
import net.anything.utils.uiBuilder.view.ViewGenerator

class ScreenBuilderImpl(
    private val viewGenerator: ViewGenerator,
    private val constraintsMaker: ConstraintsMaker,
    private val transactions: Transactions
) : ScreenBuilder {

    override fun buildContentView(fragmentManager: FragmentManager): View {
        return viewGenerator.root.apply {
            setLayoutParams(MatchParent, MatchParent)
            addToolbar(fragmentManager)
            addThingsView()
            addNewItemButton()
        }
    }

    override fun ConstraintLayout.addToolbar(fragmentManager: FragmentManager) {
        with(constraintsMaker) {
            ConstraintSet().let { set ->
                viewGenerator.apply {
                    toolbar.let { view ->
                        add(view, set) {
                            set.connectToParentStart(view)
                            set.connectToParentTop(view)
                            set.connectToParentEnd(view)
                        }
                        view.menu.addFilter {
                            transactions.apply {
                                fragmentManager.openPreferencesScreen(root)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun ConstraintLayout.addNewItemButton() {
        with(constraintsMaker) {
            ConstraintSet().apply {
                viewGenerator.addNewItemButton.let { view ->
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
                        connectToParentStart(view)
                        connectToParentEnd(view)
                        connectToUpperView(
                            view,
                            viewGenerator.toolbar
                        )
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

    override fun View.setLayoutParams(width: Int, height: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }
}