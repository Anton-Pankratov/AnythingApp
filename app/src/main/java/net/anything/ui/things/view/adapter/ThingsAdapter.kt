package net.anything.ui.things.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import net.anything.domain.entity.ShowThingEntity

class ThingsAdapter : ListAdapter<ShowThingEntity, ThingViewHolder>(ThingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        return ThingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }

}