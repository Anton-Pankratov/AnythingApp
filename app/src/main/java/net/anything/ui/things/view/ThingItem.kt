package net.anything.ui.things.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import net.anything.domain.di.locateLazy
import net.anything.entity.ShowThingEntity
import net.anything.utils.uiBuilder.MatchParent
import net.anything.utils.uiBuilder.WrapContent
import net.anything.utils.uiBuilder.size16dp
import net.anything.utils.uiBuilder.size6dp
import net.anything.utils.uiBuilder.viewGenerator.ViewGenerator

class ThingItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defAttrStyle: Int = 0
) : LinearLayout(context, attrs, defAttrStyle) {

    private val viewGenerator: ViewGenerator by locateLazy()

    init {
        setParams()
    }

    fun create(thing: ShowThingEntity) {
        with(thing) {
            viewGenerator.apply {
                addView(createThingHeader(id))
                addView(viewGenerator.createThingItem(getSigns()))
            }
        }
    }

    private fun setParams() {
        orientation = VERTICAL
        layoutParams = LayoutParams(MatchParent, WrapContent).apply {
            with(context) {
                setMargins(size16dp(), size6dp(), size16dp(), size6dp())
            }
        }
    }
}