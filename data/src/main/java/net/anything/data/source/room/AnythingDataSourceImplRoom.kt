package net.anything.data.source.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.anything.data.databases.room.RoomHelper
import net.anything.data.mapper.ThingMapper
import net.anything.domain.entity.ShowThingEntity

class AnythingDataSourceImplRoom(
    private val db: RoomHelper,
    private val mapper: ThingMapper
) : AnythingDataSourceRoom {

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