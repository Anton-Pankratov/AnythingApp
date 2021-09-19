package net.anything.domain.repository

import net.anything.domain.entity.ShowThingEntity

interface AnythingRepositorySql {

    suspend fun createThing(thing: ShowThingEntity)

    suspend fun readThings(): List<ShowThingEntity>

    suspend fun updateThing(thing: ShowThingEntity)

    suspend fun deleteThing(thing: ShowThingEntity)

    suspend fun deleteAllThings()
}