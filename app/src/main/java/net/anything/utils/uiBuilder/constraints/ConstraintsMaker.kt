package net.anything.utils.uiBuilder.constraints

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

interface ConstraintsMaker {

    fun ConstraintLayout.setConstraints(
        constraintSet: ConstraintSet, connects: () -> Unit
    ): ConstraintSet

    fun ConstraintSet.connectToUpperView(view: View, anchor: View)

    fun ConstraintSet.connectToParentTop(view: View)

    fun ConstraintSet.connectToParentBottom(view: View)

    fun ConstraintSet.connectToParentStart(view: View)

    fun ConstraintSet.connectToParentEnd(view: View)

    fun ConstraintSet.makeConnect(
        startViewId: Int, startViewSide: Int, endViewId: Int, endViewSide: Int
    )
}