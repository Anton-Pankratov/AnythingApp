package net.anything.utils.uiBuilder.screen

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.utils.uiBuilder.view.ViewGenerator
import net.anything.utils.uiParams.MatchParent

class ScreenBuilderImpl(
    private val activityContext: Context,
    private val viewGenerator: ViewGenerator
) : ScreenBuilder {

    override fun buildContentView(): View {
        return ConstraintLayout(activityContext).apply {
            setLayoutParams(MatchParent, MatchParent)
            addToolbar()
           // addNewItemButton()
        }
    }

    override fun ConstraintLayout.addToolbar() {
        viewGenerator.toolbar.let { view ->
            ConstraintSet().apply {
                setConstraints(this) {
                    makeConnect(view.id, ConstraintSet.START, this@addToolbar.id, ConstraintSet.START)
                    makeConnect(view.id, ConstraintSet.TOP, this@addToolbar.id, ConstraintSet.TOP)
                    makeConnect(view.id, ConstraintSet.END, this@addToolbar.id, ConstraintSet.END)
                }
            }
            addView(view)
        }
    }

    override fun ConstraintLayout.addNewItemButton() {
        addView(viewGenerator.addNewItemButton)
    }

    override fun ConstraintLayout.setConstraints(
        constraintSet: ConstraintSet,
        connects: () -> Unit
    ): ConstraintSet {
        return constraintSet.apply {
            clone(this@setConstraints)
            connects.invoke()
            applyTo(this@setConstraints)
        }
    }

    override fun ConstraintSet.makeConnect(
        startViewId: Int,
        startViewSide: Int,
        endViewId: Int,
        endViewSide: Int
    ) {
        connect(startViewId, startViewSide, endViewId, endViewSide)
    }

    override fun View.setLayoutParams(width: Int, height: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }
}