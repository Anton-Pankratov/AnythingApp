package net.anything.data.databases.sql.crud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.provider.BaseColumns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import net.anything.data.databases.converter.Converter
import net.anything.data.databases.sql.AnythingSqlDatabase
import net.anything.data.entities.StoredSign
import net.anything.data.entities.StoredThingEntity
import net.anything.domain.di.locate
import net.anything.domain.di.locateLazy
import kotlin.math.sign

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
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLException) {
            db.execSQL(query)
            return listOf()
        }
        with(cursor) {
            if (moveToFirst()) {
                do {
                    val sign1 = converter.toStoredSign(getString(getColumnIndex(ThingEntry.COLUMN_NAME_SIGN1)))
                    val sign2 = converter.toStoredSign(getString(getColumnIndex(ThingEntry.COLUMN_NAME_SIGN2)))
                    val sign3 = converter.toStoredSign(getString(getColumnIndex(ThingEntry.COLUMN_NAME_SIGN3)))

                    request.add(
                        StoredThingEntity(
                            id = getInt(getColumnIndex(ThingEntry.COLUMN_NAME_ID)),
                            sign1 = StoredSign(
                                header = sign1?.header,
                                value = sign1?.value,
                            ),
                            sign2 = StoredSign(
                                header = sign2?.header,
                                value = sign2?.value,
                            ),
                            sign3 = StoredSign(
                                header = sign3?.header,
                                value = sign3?.value,
                            ),
                    ))
                } while (cursor.moveToNext())
            }
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

    override fun Crud.operate(cv: ContentValues, thingId: Int?) {
        val db = db.writableDatabase
        val whereClause = "${ThingEntry.COLUMN_NAME_ID}=$thingId"
        ThingEntry.TABLE_NAME_THINGS.let { tableName ->
            db.apply {
                when (this@operate) {
                    Crud.CREATE -> insert(tableName, null, cv)
                    Crud.READ -> {}
                    Crud.UPDATE -> update(tableName, cv, whereClause, null)
                    Crud.DELETE -> delete(tableName, whereClause, null)
                }
            }
        }
    }

    private fun StoredSign?.toJson() = converter.toJson(this)
}