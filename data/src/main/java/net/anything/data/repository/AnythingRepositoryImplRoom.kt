package net.anything.data.repository

import kotlinx.coroutines.flow.Flow
import net.anything.data.source.room.AnythingDataSourceRoom
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.repository.AnythingRepositoryRoom

class AnythingRepositoryImplRoom : AnythingRepositoryRoom {

    private val dataSource: AnythingDataSourceRoom by locateLazy()

    override val thingsFlow: Flow<List<ShowThingEntity>>?
        get() = dataSource.thingsFlow

    override suspend fun saveThing(thing: ShowThingEntity) {
        dataSource.saveThing(thing)
    }

    override suspend fun updateThing(thing: ShowThingEntity) {
        dataSource.updateThing(thing)
    }

    override suspend fun deleteThing(thing: ShowThingEntity) {
        dataSource.deleteThing(thing)
    }
}