package net.anything.data.databases.sql

import android.content.ContentValues
import kotlinx.coroutines.flow.Flow
import net.anything.data.entities.StoredThingEntity

interface AnythingDbHandler {

    fun createThing(thing: StoredThingEntity)

    fun readThingsFlow(): Flow<List<StoredThingEntity>>

    fun updateThing(thing: StoredThingEntity)

    fun deleteThing(thing: StoredThingEntity)

    fun Crud.operate(cv: ContentValues, thingId: Int?)
}