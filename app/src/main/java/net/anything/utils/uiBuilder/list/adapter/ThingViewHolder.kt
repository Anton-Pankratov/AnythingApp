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

        thing.apply {
            view.create(
                mapOf(
                    sign1?.header to sign1?.value,
                    sign2?.header to sign2?.value,
                    sign3?.header to sign3?.value
                )
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup) =
            ThingItem(parent.context).let(::ThingViewHolder)
    }
}