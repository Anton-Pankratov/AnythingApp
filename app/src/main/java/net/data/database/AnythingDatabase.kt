package net.data.database

import net.data.dao.ItemDao

interface AnythingDatabase {

    val dao: ItemDao
}