package net.anything.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "things")
data class StoredThingEntity(
    @PrimaryKey val id: Int? = null,
    val sign1: StoredSign? = null,
    val sign2: StoredSign? = null,
    val sign3: StoredSign? = null
)
