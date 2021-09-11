package net.anything.data.database

import androidx.room.TypeConverter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.anything.data.entities.StoredSign

class Converter {

    @TypeConverter
    fun String.toStoredSign(): StoredSign {
        return jacksonObjectMapper().readValue(this, StoredSign::class.java)
    }

    @TypeConverter
    fun StoredSign.toJson(): String {
        return jacksonObjectMapper().writeValueAsString(this)
    }
}