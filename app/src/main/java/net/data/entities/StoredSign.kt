package net.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sign")
data class StoredSign(
    @PrimaryKey val id: Int,
    val header: String?,
    val value: String?
)