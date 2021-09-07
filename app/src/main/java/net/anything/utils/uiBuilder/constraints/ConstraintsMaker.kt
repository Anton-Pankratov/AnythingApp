package net.anything.utils.uiBuilder.constraints

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

interface ConstraintsMaker {

    fun ConstraintLayout.setConstraints(
        constraintSet: ConstraintSet, connects: () -> Unit
    ): ConstraintSet

    fun ConstraintSet.connectToTop(view: View)
    fun ConstraintSet.connectToBottom(view: View)
    fun ConstraintSet.connectToStart(view: View)
    fun ConstraintSet.connectToEnd(view: View)

    fun ConstraintSet.makeConnect(
        startViewId: Int, startViewSide: Int, endViewId: Int, endViewSide: Int
    )
}