package net.anything.ui.things.view.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.things.view.adapter.ThingsAdapter
import net.anything.ui.things.view.adapter.OnThingClickListener
import net.anything.utils.uiBuilder.MatchParent
import net.anything.utils.uiBuilder.WrapContent

class ThingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val thingsAdapter by lazy { ThingsAdapter() }

    init {
        setParams()
    }

    fun submit(
        things: List<ShowThingEntity>,
        listener: OnThingClickListener?
    ) {
        thingsAdapter.apply {
            submitList(things)
            setThingClickListener(listener)
        }
    }

    private fun setParams() {
        layoutParams = LayoutParams(MatchParent, WrapContent)
        layoutManager = LinearLayoutManager(context)
        adapter = thingsAdapter
        addItemDecoration(
            DividerItemDecoration(
                this.context, LinearLayoutManager.VERTICAL
            )
        )
    }
}