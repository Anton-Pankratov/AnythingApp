package net.anything.utils.uiBuilder.viewGenerator

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import net.anything.anythingapp.R
import net.anything.ui.things.view.recycler.ThingsView
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode.OnChangeDbModeListener
import net.anything.utils.dbMode.currentDbMode
import net.anything.utils.getPrimaryColor
import net.anything.utils.getStringRes
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.transactions.Screens
import net.anything.utils.uiBuilder.MatchParent
import net.anything.utils.uiBuilder.WrapContent
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

    override fun Menu.addDbChange(listener: OnChangeDbModeListener) {
        add(0, 1, Menu.NONE, currentDbMode.name)
            .apply {
                setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                setOnMenuItemClickListener {
                    setBy(currentDbMode, listener)
                    return@setOnMenuItemClickListener true
                }
            }
    }

    private fun MenuItem.setBy(mode: DatabaseMode, listener: OnChangeDbModeListener) {
        when (mode) {
            DatabaseMode.ROOM -> DatabaseMode.NATIVE.setFor(this, listener)
            DatabaseMode.NATIVE -> DatabaseMode.ROOM.setFor(this, listener)
        }
    }

    private fun DatabaseMode.setFor(menuItem: MenuItem, listener: OnChangeDbModeListener) {
        menuItem.title = name
        listener.onChange(this)
        currentDbMode = this
    }

    /**
     * Things Fragment
     */

    // Buttons

    override val createNewThingButton: MaterialButton by lazy {
        MaterialButton(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, WrapContent)
                tag = "create_thing"
                text = getStringRes(R.string.button_create_new_thing)
                background = getPrimaryColor()
                setTextColor(Color.WHITE)
            }
        }
    }

    override val deleteAllThingsButton: MaterialButton by lazy {
        MaterialButton(context).apply {
            id = generateId()
            context.apply {
                setLayoutParams(MatchParent, WrapContent)
                tag = "delete_thing"
                text = getStringRes(R.string.button_delete_all_things)
                background = null
                setTextColor(Color.RED)
            }
        }
    }

    // Recycler View

    override val thingsView: ThingsView by lazy {
        ThingsView(context).apply {
            id = generateId()
            setLayoutParams(MatchParent, 0)
        }
    }

    // Placeholder

    override val emptyThingsPlaceholder: TextView =
        TextView(context).apply {
            id = generateId()
            setLayoutParams(WrapContent, WrapContent)
            text = resources.getString(
                R.string.placeholder_no_things
            )
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