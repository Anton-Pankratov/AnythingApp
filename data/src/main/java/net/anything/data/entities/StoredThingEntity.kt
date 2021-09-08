package net.anything.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class StoredThingEntity(
    @PrimaryKey val id: Int,
    val sign1: StoredSign? = null,
    val sign2: StoredSign? = null,
    val sign3: StoredSign? = null
)
