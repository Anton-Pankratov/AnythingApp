package net.anything.utils.uiBuilder.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout
import net.anything.domain.di.locateLazy
import net.anything.utils.uiBuilder.view.ViewGenerator

class ThingItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defAttrStyle: Int = 0
) : LinearLayout(context, attrs, defAttrStyle) {

    private val viewGenerator: ViewGenerator by locateLazy()

    init {
        orientation = VERTICAL
    }

    fun create(signs: Map<String?, String?>) {
        addView(viewGenerator.createThingItem(signs))
    }
}