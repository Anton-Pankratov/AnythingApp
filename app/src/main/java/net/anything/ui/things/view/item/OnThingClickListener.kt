package net.anything.ui.things.view.item

import net.anything.domain.entity.ShowThingEntity

fun interface OnThingClickListener {

    fun onClick(thing: ShowThingEntity?)
}