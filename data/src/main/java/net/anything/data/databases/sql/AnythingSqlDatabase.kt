package net.anything.data.databases.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.anything.data.databases.sql.crud.ThingEntry

class AnythingSqlDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

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

    private companion object {
        const val DATABASE_NAME = "AnythingDb"
        const val DATABASE_VERSION = 1

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ThingEntry.TABLE_NAME_THINGS}"

        const val SQL_CREATE_ENTRIES = "CREATE TABLE ${ThingEntry.TABLE_NAME_THINGS} (" +
                "${ThingEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                "${ThingEntry.COLUMN_NAME_SIGN1} TEXT," +
                "${ThingEntry.COLUMN_NAME_SIGN2} TEXT," +
                "${ThingEntry.COLUMN_NAME_SIGN3} TEXT)"
    }
}