package net.anything.ui.things.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.things.view.item.OnThingClickListener
import net.anything.ui.things.view.item.ThingItem

class ThingViewHolder(
    private val view: ThingItem
) : RecyclerView.ViewHolder(view) {

    var thing: ShowThingEntity? = null
        private set

    fun onBind(
        thing: ShowThingEntity?,
        listener: OnThingClickListener?
    ) {
        this.thing = thing
        view.create(thing, listener)
    }

    companion object {
        fun create(parent: ViewGroup) =
            ThingItem(parent.context).let(::ThingViewHolder)
    }
}