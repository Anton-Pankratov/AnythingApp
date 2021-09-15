package net.anything.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import net.anything.data.entities.StoredThingEntity
import net.anything.domain.entity.ShowThingEntity

interface AnyThingDataSource {

    val thingsFlow: Flow<List<ShowThingEntity>>

    suspend fun saveThing(thing: ShowThingEntity)

    suspend fun updateThing(thing: ShowThingEntity)

    suspend fun deleteThing(thing: ShowThingEntity)
}