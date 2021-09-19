package net.anything.data.source.sql

import net.anything.domain.entity.ShowThingEntity

interface AnythingDataSourceSql {

    suspend fun saveThing(thing: ShowThingEntity)

    suspend fun readThings(): List<ShowThingEntity>

    suspend fun updateThing(thing: ShowThingEntity)

    suspend fun deleteThing(thing: ShowThingEntity)

    suspend fun deleteAllThings()
}