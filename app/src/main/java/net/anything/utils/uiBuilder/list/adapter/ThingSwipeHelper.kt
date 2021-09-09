package net.anything.utils.uiBuilder.list.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import net.anything.entity.ShowThingEntity

class ThingSwipeHelper(onSwiped: (ShowThingEntity) -> Unit) :
    ItemTouchHelper(ThingSwipeCallback(onSwiped))