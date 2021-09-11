package net.anything.utils.uiBuilder.list

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.anything.entity.ShowThingEntity
import net.anything.utils.toDp
import net.anything.utils.uiBuilder.list.adapter.ThingsAdapter
import net.anything.utils.uiBuilder.sizes.MatchParent
import net.anything.utils.uiBuilder.sizes.WrapContent
import net.anything.utils.uiBuilder.sizes.margin16dp

class ThingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val thingsAdapter by lazy { ThingsAdapter() }

    init { setParams() }

    fun submit(things: List<ShowThingEntity>) {
        thingsAdapter.submitList(things)
    }

    private fun setParams() {
        layoutParams = LayoutParams(MatchParent, WrapContent)
        layoutManager = LinearLayoutManager(context)
        adapter = thingsAdapter
    }
}