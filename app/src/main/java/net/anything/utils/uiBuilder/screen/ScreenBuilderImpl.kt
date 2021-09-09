package net.anything.utils.uiBuilder.screen

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.utils.uiBuilder.constraints.ConstraintsMaker
import net.anything.utils.uiBuilder.sizes.MatchParent
import net.anything.utils.uiBuilder.view.ViewGenerator

class ScreenBuilderImpl(
    private val activityContext: Context,
    private val viewGenerator: ViewGenerator,
    private val constraintsMaker: ConstraintsMaker
) : ScreenBuilder {

    override fun buildContentView(): View {
        return ConstraintLayout(activityContext).apply {
            setLayoutParams(MatchParent, MatchParent)
            addToolbar()
            addThingsView()
            addNewItemButton()
        }
    }

    override fun ConstraintLayout.addToolbar() {
        with(constraintsMaker) {
            ConstraintSet().apply {
                viewGenerator.toolbar.let { view ->
                    add(view, this) {
                        connectToParentStart(view)
                        connectToParentTop(view)
                        connectToParentEnd(view)
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
                        connectToUpperView(view,
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