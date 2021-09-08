package net.anything.utils.uiBuilder.list

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.anything.entity.ShowThingEntity
import net.anything.utils.uiBuilder.list.adapter.ThingsAdapter

class ThingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val thingsAdapter by lazy { ThingsAdapter() }

    init {
        layoutManager = LinearLayoutManager(context)
    }

    fun submit(things: List<ShowThingEntity>) {
        thingsAdapter.submitList(things)
    }
}