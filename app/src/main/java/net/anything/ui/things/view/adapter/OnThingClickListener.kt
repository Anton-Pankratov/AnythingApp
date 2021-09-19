package net.anything.ui.things.view.adapter

import net.anything.domain.entity.ShowThingEntity

fun interface OnThingClickListener {

    fun onClick(thing: ShowThingEntity?)
}