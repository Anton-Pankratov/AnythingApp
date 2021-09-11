package net.anything.utils.uiBuilder.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import net.anything.anythingapp.R
import net.anything.utils.getPrimaryColor
import net.anything.utils.getStringRes
import net.anything.utils.uiBuilder.list.ThingItem
import net.anything.utils.uiBuilder.list.ThingsView
import net.anything.utils.uiBuilder.sizes.MatchParent
import net.anything.utils.uiBuilder.sizes.WrapContent
import net.anything.utils.uiBuilder.sizes.actionBarSize
import net.anything.utils.uiBuilder.sizes.margin10dp
import java.util.concurrent.atomic.AtomicInteger

class ViewGeneratorImpl(private val context: Context) : ViewGenerator {

    override val root: ConstraintLayout by lazy {
        ConstraintLayout(context).apply {
            id = generateId()
            setLayoutParams(MatchParent, MatchParent)
        }
    }

    override val toolbar: MaterialToolbar by lazy {
        MaterialToolbar(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, actionBarSize)
                title = getStringRes(R.string.app_name)
                isTitleCentered = true
                background = getPrimaryColor()
                setTitleTextColor(Color.WHITE)
            }
        }
    }

    override val addNewItemButton: MaterialButton by lazy {
        MaterialButton(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, WrapContent)
                text = getStringRes(R.string.button_add_new_thing)
                background = getPrimaryColor()
                setTextColor(Color.WHITE)
            }
        }
    }
    override val thingsView: ThingsView by lazy {
        ThingsView(context).apply {
            id = generateId()
        }
    }

    override fun Menu.addFilter(listener: ViewGenerator.OnFilterClickListener) {
        add(0, 1, Menu.NONE, "Sort").apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            setIcon(R.drawable.ic_filter)
            setOnMenuItemClickListener {
                listener.onClick()
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun createThingItem(signs: Map<String?, String?>): ThingItem {
        return ThingItem(context).apply {
            signs.entries.forEach {
                addView(createThingSign(it.key, it.value))
            }
        }
    }

    override fun createThingSign(header: String?, value: String?): TextView {
        val signText = "$header: $value"
        return TextView(context).apply {
            setSignLayoutParams(WrapContent, WrapContent, context.margin10dp())
            text = signText
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

    override fun TextView.setSignLayoutParams(width: Int, height: Int, margin: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height).apply {
            setMargins(0, 0, 0, margin)
        }
    }

    override fun View.setLayoutParams(width: Int, height: Int) {
        layoutParams = LinearLayout.LayoutParams(width, height)
    }
}