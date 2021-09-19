package net.anything.data.repository

import net.anything.data.source.sql.AnythingDataSourceSql
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.repository.AnythingRepositorySql

class AnythingRepositoryImplSql : AnythingRepositorySql {

    private val dataSource: AnythingDataSourceSql by locateLazy()

    override suspend fun createThing(thing: ShowThingEntity) {
        dataSource.saveThing(thing)
    }

    override suspend fun readThings(): List<ShowThingEntity> {
        return dataSource.readThings()
    }

    override suspend fun updateThing(thing: ShowThingEntity) {
        dataSource.saveThing(thing)
    }

    override suspend fun deleteThing(thing: ShowThingEntity) {
        dataSource.deleteThing(thing)
    }

    override suspend fun deleteAllThings() {
        dataSource.deleteAllThings()
    }
}