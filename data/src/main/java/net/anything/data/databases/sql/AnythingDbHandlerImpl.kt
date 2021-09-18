package net.anything.data.databases.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import net.anything.data.databases.converter.Converter
import net.anything.data.entities.StoredSign
import net.anything.data.entities.StoredThingEntity
import net.anything.domain.di.locateLazy

class AnythingDbHandlerImpl(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), AnythingDbHandler {

    private val converter: Converter by locateLazy()

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    override fun createThing(thing: StoredThingEntity) {
        with(thing) {
            ContentValues().apply {
                put(BaseColumns._ID, id)
                put(ThingEntry.COLUMN_NAME_SIGN1, sign1.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN2, sign2.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN3, sign3.toJson())

                Crud.CREATE.operate(this, id)

            }
        }
    }

    override fun readThingsFlow(): Flow<List<StoredThingEntity>> {
        return flowOf()
    }

    override fun updateThing(thing: StoredThingEntity) {
        with(thing) {
            ContentValues().apply {
                put(BaseColumns._ID, id)
                put(ThingEntry.COLUMN_NAME_SIGN1, sign1.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN2, sign2.toJson())
                put(ThingEntry.COLUMN_NAME_SIGN3, sign3.toJson())

                Crud.UPDATE.operate(this, id)
            }
        }
    }

    override fun deleteThing(thing: StoredThingEntity) {
        with(thing) {
            ContentValues().apply {
                put(BaseColumns._ID, id)

                Crud.DELETE.operate(this, id)
            }
        }
    }

    override fun Crud.operate(cv: ContentValues, thingId: Int?) {
        val whereClause = "${BaseColumns._ID}=$thingId"
        ThingEntry.TABLE_NAME_THINGS.let { tableName ->
            writableDatabase.apply {
                when (this@operate) {
                    Crud.CREATE -> insert(tableName, null, cv)
                    Crud.READ -> {}
                    Crud.UPDATE -> update(tableName, cv, whereClause, null)
                    Crud.DELETE -> delete(tableName, whereClause, null)
                }
                close()
            }
        }
    }

    private fun StoredSign?.toJson() = converter.toJson(this)

    private companion object {
        const val DATABASE_NAME = "AnythingDb"
        const val DATABASE_VERSION = 1

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ThingEntry.TABLE_NAME_THINGS}"

        const val SQL_CREATE_ENTRIES = "CREATE TABLE ${ThingEntry.TABLE_NAME_THINGS} (" +
                "${BaseColumns._ID} INTEGER_PRIMARY_KEY," +
                "${ThingEntry.COLUMN_NAME_SIGN1} TEXT," +
                "${ThingEntry.COLUMN_NAME_SIGN2} TEXT," +
                "${ThingEntry.COLUMN_NAME_SIGN3} TEXT)"
    }

    private object ThingEntry : BaseColumns {
        const val TABLE_NAME_THINGS = "things"
        const val COLUMN_NAME_SIGN1 = "sign1"
        const val COLUMN_NAME_SIGN2 = "sign2"
        const val COLUMN_NAME_SIGN3 = "sign3"
    }
}