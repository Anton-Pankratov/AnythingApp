package net.anything.data.databases.sql.crud

import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import net.anything.data.databases.converter.Converter
import net.anything.data.databases.sql.AnythingSqlDatabase
import net.anything.data.entities.StoredSign
import net.anything.data.entities.StoredThingEntity

class ThingsCrudImpl(
    private val db: AnythingSqlDatabase,
    private val converter: Converter
) : ThingsCrud {

    override suspend fun createThing(thing: StoredThingEntity) {
        with(thing) {
            ContentValues().apply {
                put(ThingEntry.COLUMN_NAME_ID, id)
                put(ThingEntry.COLUMN_NAME_SIGN1, sign1.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN2, sign2.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN3, sign3.toJson())

                Crud.CREATE.operate(this, id)
            }
        }
    }

    override suspend fun readThings(): List<StoredThingEntity> {
        val db = db.readableDatabase
        val query = "SELECT * FROM ${ThingEntry.TABLE_NAME_THINGS}"
        val request = mutableListOf<StoredThingEntity>()
        val cursor: Cursor
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLException) {
            db.execSQL(query)
            return listOf()
        }
        with(cursor) {
            if (moveToFirst()) {
                do request.add(formThing())
                while (cursor.moveToNext())
            }
            try {
                close()
            } catch (e: Exception) {}
        }
        return request.toList()
    }

    override suspend fun updateThing(thing: StoredThingEntity) {
        with(thing) {
            ContentValues().apply {
                put(ThingEntry.COLUMN_NAME_ID, id)
                put(ThingEntry.COLUMN_NAME_SIGN1, sign1.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN2, sign2.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN3, sign3.toJson())

                Crud.UPDATE.operate(this, id)
            }
        }
    }

    override suspend fun deleteThing(thing: StoredThingEntity) {
        with(thing) {
            ContentValues().apply {
                put(ThingEntry.COLUMN_NAME_ID, id)

                Crud.DELETE.operate(this, id)
            }
        }
    }

    override suspend fun deleteAllThings() {
        val db = db.writableDatabase
        db.execSQL("DELETE FROM ${ThingEntry.TABLE_NAME_THINGS}")
        try {
            db.close()
        } catch (e: Exception) {}
    }

    override fun Crud.operate(cv: ContentValues, thingId: Int?) {
        val db = db.writableDatabase
        val whereClause = "${ThingEntry.COLUMN_NAME_ID}=$thingId"
        ThingEntry.TABLE_NAME_THINGS.let { tableName ->
            db.apply {
                when (this@operate) {
                    Crud.CREATE -> insert(tableName, null, cv)
                    Crud.UPDATE -> update(tableName, cv, whereClause, null)
                    Crud.DELETE -> delete(tableName, whereClause, null)
                }
                try {
                    close()
                } catch (e: Exception) {}
            }
        }
    }

    private fun StoredSign?.toJson() = converter.toJson(this)

    private fun Cursor.formThing(): StoredThingEntity {
        return StoredThingEntity(
            id = getInt(getColumnIndex(ThingEntry.COLUMN_NAME_ID)),
            sign1 = formSign(ThingEntry.COLUMN_NAME_SIGN1),
            sign2 = formSign(ThingEntry.COLUMN_NAME_SIGN2),
            sign3 = formSign(ThingEntry.COLUMN_NAME_SIGN3)
        )
    }

    private fun Cursor.formSign(column: String): StoredSign {
        converter.toStoredSign(getString(getColumnIndex(column)))
            .apply {
                return StoredSign(
                    header = this?.header,
                    value = this?.value
                )
            }
    }
}