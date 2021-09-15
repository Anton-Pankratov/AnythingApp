package net.anything.ui.things.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.anything.domain.entity.ShowThingEntity
import net.anything.ui.things.view.ThingItem

class ThingViewHolder(
    private val view: ThingItem
) : RecyclerView.ViewHolder(view) {

    var thing: ShowThingEntity? = null
        private set

    fun onBind(
        thing: ShowThingEntity?,
    ) {
        this.thing = thing
        view.create(thing)
    }

    companion object {
        fun create(parent: ViewGroup) =
            ThingItem(parent.context).let(::ThingViewHolder)
    }
}