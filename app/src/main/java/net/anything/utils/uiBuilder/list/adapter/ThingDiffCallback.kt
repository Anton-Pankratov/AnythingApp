package net.anything.utils.uiBuilder.list.adapter

import androidx.recyclerview.widget.DiffUtil
import net.anything.entity.ShowThingEntity

class ThingDiffCallback : DiffUtil.ItemCallback<ShowThingEntity>() {

    override fun areItemsTheSame(oldItem: ShowThingEntity, newItem: ShowThingEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShowThingEntity, newItem: ShowThingEntity): Boolean {
        return oldItem == newItem
    }
}