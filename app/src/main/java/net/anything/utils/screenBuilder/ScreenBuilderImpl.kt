package net.anything.utils.screenBuilder

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import net.anything.anythingapp.R
import net.anything.utils.getStringRes
import net.anything.utils.uiParams.MatchParent
import net.anything.utils.uiParams.actionBarSize

class ScreenBuilderImpl(private val activityContext: Context) : ScreenBuilder {

    override fun buildContentView(): View {
        return LinearLayout(activityContext).apply {
            setLayoutParams(MatchParent, MatchParent)
            addToolbar()
        }
    }

    override fun LinearLayout.addToolbar() {
        context.apply {
            addView(MaterialToolbar(this).apply {
                setLayoutParams(MatchParent, actionBarSize)
                background = ContextCompat.getDrawable(context, R.color.design_default_color_primary_variant)
                title = getStringRes(R.string.app_name)
                isTitleCentered = true
                setTitleTextColor(Color.WHITE)
            })
        }
    }

    override fun View.setLayoutParams(width: Int, height: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }
}