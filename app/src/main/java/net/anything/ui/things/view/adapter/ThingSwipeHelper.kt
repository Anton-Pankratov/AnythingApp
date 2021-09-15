package net.anything.ui.things.view.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import net.anything.domain.entity.ShowThingEntity

class ThingSwipeHelper(onSwiped: (ShowThingEntity) -> Unit) :
    ItemTouchHelper(ThingSwipeCallback(onSwiped))