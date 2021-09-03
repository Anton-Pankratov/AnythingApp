package net.anything.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class StoredItem(
    @PrimaryKey val id: Int,
    val sign1: String?,
    val sign2: String?,
    val sign3: String?
)
