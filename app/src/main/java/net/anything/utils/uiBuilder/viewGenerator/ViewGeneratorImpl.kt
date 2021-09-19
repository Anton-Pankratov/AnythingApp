package net.anything.utils.uiBuilder.viewGenerator

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.TypefaceCompat
import com.google.android.material.button.MaterialButton
import net.anything.anythingapp.R
import net.anything.domain.entity.ShowSign
import net.anything.ui.things.view.item.ThingItem
import net.anything.ui.things.view.recycler.ThingsView
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode.OnChangeDbModeListener
import net.anything.utils.dbMode.currentUseDb
import net.anything.utils.getPrimaryColor
import net.anything.utils.getStringRes
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.transactions.Screens
import net.anything.utils.uiBuilder.MatchParent
import net.anything.utils.uiBuilder.WrapContent
import net.anything.utils.uiBuilder.size6dp
import java.util.concurrent.atomic.AtomicInteger

class ViewGeneratorImpl(private val context: Context) : ViewGenerator {

    /**
     * Main Activity
     */

    override val fragmentContainer: FrameLayout by lazy {
        FrameLayout(context).apply {
            id = generateId()
            setLayoutParams(MatchParent, MatchParent)
        }
    }

    /**
     * Root Layout for Fragments
     */

    override val root: ConstraintLayout by lazy {
        ConstraintLayout(context).apply {
            id = generateId()
            setLayoutParams(MatchParent, MatchParent)
        }
    }

    /**
     * Action Bar Settings
     */

    override fun Menu.addDbChange(listener: OnChangeDbModeListener) {
        add(
            0, 1, Menu.NONE,
            if (currentUseDb == DatabaseMode.ROOM) {
                DatabaseMode.ROOM.name
            } else {
                DatabaseMode.NATIVE.name
            }
        ).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            setOnMenuItemClickListener {
                if (currentUseDb == DatabaseMode.ROOM) {
                    DatabaseMode.NATIVE.apply {
                        title = name
                        listener.onChange(this)
                    }
                } else {
                    DatabaseMode.ROOM.apply {
                        title = name
                        listener.onChange(this)
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun Menu.addFilter(listener: OnTransaction) {
        add(0, 2, Menu.NONE, "Sort").apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            setIcon(R.drawable.ic_filter)
            setOnMenuItemClickListener {
                listener.begin(Screens.FILTER, null)
                return@setOnMenuItemClickListener true
            }
        }
    }

    /**
     * Things Fragment
     */

    // Button

    override val addNewItemButton: MaterialButton by lazy {
        MaterialButton(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, WrapContent)
                tag = "create_thing"
                text = getStringRes(R.string.button_add_new_thing)
                background = getPrimaryColor()
                setTextColor(Color.WHITE)
            }
        }
    }

    // Recycler View

    override val thingsView: ThingsView by lazy {
        ThingsView(context).apply {
            id = generateId()
        }
    }

    // Item for Recycler View

    override fun createThingItem(signs: List<ShowSign?>): ThingItem {
        return ThingItem(context).apply {
            signs.forEach {
                createThingSign(it?.header, it?.value).apply {
                    if (this != null) addView(this)
                }
            }
        }
    }

    override fun createThingHeader(id: Int?): TextView {
        return TextView(context).apply {
            typeface = TypefaceCompat.create(context, Typeface.MONOSPACE, Typeface.BOLD_ITALIC)
            "Thing #$id".let { header -> text = header }
        }
    }

    override fun createThingSign(header: String?, value: String?): TextView? {
        val signText = "$header : $value"
        return if (!header.isNullOrEmpty() || !value.isNullOrEmpty()) {
            TextView(context).apply {
                setSignLayoutParams(WrapContent, WrapContent, context.size6dp())
                text = signText
            }
        } else null
    }

    /**
     * Common
     */

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