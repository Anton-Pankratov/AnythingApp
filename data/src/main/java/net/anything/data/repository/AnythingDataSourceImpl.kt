package net.anything.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.anything.data.databases.room.AnythingDatabase
import net.anything.data.mapper.ThingMapper
import net.anything.domain.entity.ShowThingEntity

class AnythingDataSourceImpl(
    private val db: AnythingDatabase,
    private val mapper: ThingMapper
) : AnyThingDataSource {

    override val thingsFlow: Flow<List<ShowThingEntity>>?
        get() = db.dao?.thingsFlow()?.map { things ->
            things.map {
                mapper.toShowThing(it)
            }
        }

    override suspend fun saveThing(thing: ShowThingEntity) {
        db.dao?.saveThing(mapper.toStoredThing(thing))
    }

    override suspend fun updateThing(thing: ShowThingEntity) {
        db.dao?.updateThing(mapper.toStoredThing(thing))
    }

    override suspend fun deleteThing(thing: ShowThingEntity) {
        db.dao?.deleteThing(mapper.toStoredThing(thing))
    }


}