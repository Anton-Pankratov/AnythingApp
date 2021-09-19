package net.anything.data.source.sql

import net.anything.data.databases.sql.crud.ThingsCrud
import net.anything.data.mapper.ThingMapper
import net.anything.domain.entity.ShowThingEntity

class AnythingDataSourceImplSql(
    private val crud: ThingsCrud,
    private val mapper: ThingMapper
) : AnythingDataSourceSql {

    override suspend fun saveThing(thing: ShowThingEntity) {
        crud.createThing(thing.toStored())
    }

    override suspend fun readThings(): List<ShowThingEntity> {
        return crud.readThings().map { thing ->
            mapper.toShowThing(thing)
        }
    }

    override suspend fun updateThing(thing: ShowThingEntity) {
        crud.updateThing(thing.toStored())
    }

    override suspend fun deleteThing(thing: ShowThingEntity) {
        crud.deleteThing(thing.toStored())
    }

    override suspend fun deleteAllThings() {
        crud.deleteAllThings()
    }

    private fun ShowThingEntity.toStored() = mapper.toStoredThing(this)
}