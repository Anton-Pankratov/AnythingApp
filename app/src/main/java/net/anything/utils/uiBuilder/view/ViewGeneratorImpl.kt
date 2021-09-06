package net.anything.utils.uiBuilder.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import net.anything.anythingapp.R
import net.anything.utils.getStringRes
import net.anything.utils.uiParams.MatchParent
import net.anything.utils.uiParams.WrapContent
import net.anything.utils.uiParams.actionBarSize
import java.util.concurrent.atomic.AtomicInteger

class ViewGeneratorImpl(private val context: Context) : ViewGenerator {

    override val toolbar: MaterialToolbar by lazy {
        MaterialToolbar(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, actionBarSize)
                title = getStringRes(R.string.app_name)
                isTitleCentered = true
                setTitleTextColor(Color.WHITE)
                background = ContextCompat.getDrawable(
                    context, R.color.design_default_color_primary_variant
                )
            }
        }
    }

    override val addNewItemButton: MaterialButton by lazy {
        MaterialButton(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, WrapContent)
                text = getStringRes(R.string.button_add_new_thing)
                setTextColor(Color.WHITE)
                background = ContextCompat.getDrawable(context, R.color.design_default_color_primary_variant)
            }
        }
    }

    override fun generateId(): Int {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            return View.generateViewId()
        } else {
            val integer = AtomicInteger(1)
            while (true) {
                val result = integer.get()
                var newValue = result + 1
                if (newValue > 0x00FFFFFF) newValue = 1
                if (integer.compareAndSet(result, newValue)) {
                    return result
                }
            }
        }
    }

    override fun View.setLayoutParams(width: Int, height: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }
}