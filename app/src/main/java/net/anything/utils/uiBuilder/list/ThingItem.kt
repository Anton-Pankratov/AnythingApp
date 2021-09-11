package net.anything.utils.uiBuilder.list

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout
import net.anything.domain.di.locateLazy
import net.anything.utils.uiBuilder.sizes.MatchParent
import net.anything.utils.uiBuilder.sizes.WrapContent
import net.anything.utils.uiBuilder.sizes.margin10dp
import net.anything.utils.uiBuilder.sizes.margin16dp
import net.anything.utils.uiBuilder.view.ViewGenerator

class ThingItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defAttrStyle: Int = 0
) : LinearLayout(context, attrs, defAttrStyle) {

    private val viewGenerator: ViewGenerator by locateLazy()

    init {
        setParams()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        Paint().apply {
            color = Color.BLACK
            strokeWidth = 2f
            canvas?.drawLine(0f, height.toFloat(), width.toFloat(), height.toFloat(), this)
        }
    }

    fun create(signs: Map<String?, String?>) {
        addView(viewGenerator.createThingItem(signs))
    }

    private fun setParams() {
        orientation = VERTICAL
        layoutParams = LayoutParams(MatchParent, WrapContent).apply {
            with(context) {
                setMargins(margin16dp(), margin10dp(), margin16dp(), 0)
            }
        }
    }
}