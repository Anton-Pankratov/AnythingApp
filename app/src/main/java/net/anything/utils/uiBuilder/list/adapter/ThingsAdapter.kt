package net.anything.utils.uiBuilder.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import net.anything.entity.ShowThingEntity

class ThingsAdapter : ListAdapter<ShowThingEntity, ThingViewHolder>(ThingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        return ThingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }

}