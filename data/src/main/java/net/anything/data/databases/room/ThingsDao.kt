package net.anything.data.databases.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import net.anything.data.entities.StoredThingEntity

@Dao
interface ThingsDao {

    @Query("SELECT * FROM things")
    fun thingsFlow(): Flow<List<StoredThingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveThing(thing: StoredThingEntity)

    @Update
    suspend fun updateThing(thing: StoredThingEntity)

    @Delete
    suspend fun deleteThing(thing: StoredThingEntity)

    @Query("DELETE FROM things")
    suspend fun deleteAllThings()
}