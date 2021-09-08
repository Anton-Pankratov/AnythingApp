package net.anything.utils.uiBuilder.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.anything.entity.ShowThingEntity
import net.anything.utils.uiBuilder.list.ThingItem

class ThingViewHolder(private val view: ThingItem) : RecyclerView.ViewHolder(view) {

    var thing: ShowThingEntity? = null
        private set

    fun onBind(thing: ShowThingEntity) {
        this.thing = thing
    }

    companion object {
        fun create(parent: ViewGroup) =
            ThingItem(parent.context).let(::ThingViewHolder)
    }
}