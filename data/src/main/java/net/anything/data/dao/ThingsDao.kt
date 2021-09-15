package net.anything.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import net.anything.data.entities.StoredThingEntity

@Dao
interface ThingsDao {

    @Query("SELECT * FROM things")
    fun thingsFlow(): Flow<List<StoredThingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveThing(thing: StoredThingEntity)

    @Update(entity = StoredThingEntity::class)
    suspend fun updateThing(thing: StoredThingEntity)

    @Delete(entity = StoredThingEntity::class)
    suspend fun deleteThing(thing: StoredThingEntity)
}