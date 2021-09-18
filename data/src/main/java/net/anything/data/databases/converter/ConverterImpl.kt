package net.anything.data.databases.converter

import androidx.room.TypeConverter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.anything.data.entities.StoredSign

class ConverterImpl : Converter {

    @TypeConverter
    override fun toStoredSign(json: String?): StoredSign? {
        return jacksonObjectMapper().readValue(json, StoredSign::class.java)
    }

    @TypeConverter
    override fun toJson(sign: StoredSign?): String? {
        return jacksonObjectMapper().writeValueAsString(sign)
    }
}