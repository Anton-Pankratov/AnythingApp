package net.anything.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.anything.data.entities.StoredThingEntity
import net.anything.data.entities.StoredSign

@Database(entities = [StoredThingEntity::class, StoredSign::class], version = 1, exportSchema = false)
abstract class AnythingDatabaseImpl : RoomDatabase(), AnythingDatabase {

    companion object {
        fun build(context: Context) =
            Room.databaseBuilder(context, AnythingDatabaseImpl::class.java, "database").build()
    }
}