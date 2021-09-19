package net.anything.data.databases.sql.crud

import android.content.ContentValues
import kotlinx.coroutines.flow.Flow
import net.anything.data.databases.sql.crud.Crud
import net.anything.data.entities.StoredThingEntity

interface ThingsCrud {

    suspend fun readThings(): List<StoredThingEntity>

    suspend fun createThing(thing: StoredThingEntity)

    suspend fun updateThing(thing: StoredThingEntity)

    suspend fun deleteThing(thing: StoredThingEntity)

    suspend fun deleteAllThings()

    fun Crud.operate(cv: ContentValues, thingId: Int?)
}