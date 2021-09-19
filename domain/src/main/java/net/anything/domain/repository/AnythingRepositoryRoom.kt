package net.anything.domain.repository

import kotlinx.coroutines.flow.Flow
import net.anything.domain.entity.ShowThingEntity

interface AnythingRepositoryRoom {

    val thingsFlow: Flow<List<ShowThingEntity>>?

    suspend fun saveThing(thing: ShowThingEntity)

    suspend fun updateThing(thing: ShowThingEntity)

    suspend fun deleteThing(thing: ShowThingEntity)

    suspend fun deleteAllThings()
}