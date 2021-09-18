package net.anything.data.databases.converter

import net.anything.data.entities.StoredSign

interface Converter {

    fun toStoredSign(json: String?): StoredSign?

    fun toJson(sign: StoredSign?): String?
}