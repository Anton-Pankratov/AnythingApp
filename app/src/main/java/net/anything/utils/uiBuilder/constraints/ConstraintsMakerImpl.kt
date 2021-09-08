package net.anything.utils.uiBuilder.constraints

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class ConstraintsMakerImpl : ConstraintsMaker {

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

    override fun ConstraintSet.connectToTop(view: View) {
        makeConnect(view.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
    }

    override fun ConstraintSet.connectToBottom(view: View) {
        makeConnect(view.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
    }

    override fun ConstraintSet.connectToStart(view: View) {
        makeConnect(view.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
    }

    override fun ConstraintSet.connectToEnd(view: View) {
        makeConnect(view.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
    }

    override fun ConstraintSet.makeConnect(
        startViewId: Int,
        startViewSide: Int,
        endViewId: Int,
        endViewSide: Int
    ) {
        connect(startViewId, startViewSide, endViewId, endViewSide)
    }
}