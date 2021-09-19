package net.anything.data.databases.sql.crud

import android.provider.BaseColumns

object ThingEntry : BaseColumns {
    const val TABLE_NAME_THINGS = "things"
    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_SIGN1 = "sign1"
    const val COLUMN_NAME_SIGN2 = "sign2"
    const val COLUMN_NAME_SIGN3 = "sign3"
}