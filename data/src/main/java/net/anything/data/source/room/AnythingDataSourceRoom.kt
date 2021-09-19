package net.anything.data.source.room

import kotlinx.coroutines.flow.Flow
import net.anything.domain.entity.ShowThingEntity

interface AnythingDataSourceRoom {

    val thingsFlow: Flow<List<ShowThingEntity>>?

    suspend fun saveThing(thing: ShowThingEntity)

    suspend fun updateThing(thing: ShowThingEntity)

    suspend fun deleteThing(thing: ShowThingEntity)

    suspend fun deleteAllThings()
}