package net.anything.utils.uiBuilder.screen

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import net.anything.utils.uiBuilder.view.ViewGenerator
import net.anything.utils.uiParams.MatchParent
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class ScreenBuilderImpl(
    private val activityContext: Context,
    private val viewGenerator: ViewGenerator
) : ScreenBuilder {

    override fun buildContentView(): View {
        return ConstraintLayout(activityContext).apply {
            setLayoutParams(MatchParent, MatchParent)
            addToolbar()
            addNewItemButton()
        }
    }

    override fun ConstraintLayout.addToolbar() {
        viewGenerator.toolbar.let { view ->
            ConstraintSet().apply {
               add(view,this) {
                    makeConnect(
                        view.id, ConstraintSet.START,
                        this@addToolbar.id, ConstraintSet.START
                    )
                    makeConnect(
                        view.id, ConstraintSet.TOP,
                        this@addToolbar.id, ConstraintSet.TOP
                    )
                    makeConnect(
                        view.id, ConstraintSet.END,
                        this@addToolbar.id, ConstraintSet.END
                    )
                }
            }
        }
    }

    override fun ConstraintLayout.addNewItemButton() {
        viewGenerator.addNewItemButton.let { view ->
            ConstraintSet().apply {
                add(view, this) {
                    makeConnect(
                        view.id, ConstraintSet.START,
                        this@addNewItemButton.id, ConstraintSet.START
                    )
                    makeConnect(
                        view.id, ConstraintSet.BOTTOM,
                        this@addNewItemButton.id, ConstraintSet.BOTTOM
                    )
                    makeConnect(
                        view.id, ConstraintSet.END,
                        this@addNewItemButton.id, ConstraintSet.END
                    )
                }
            }
        }
    }

    override fun ConstraintLayout.add(
        view: View,
        constraintSet: ConstraintSet,
        connects: () -> Unit
    ) {
        view.let {
            constraintSet.apply {
                setConstraints(this) { connects.invoke() }
            }
            addView(it)
        }
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