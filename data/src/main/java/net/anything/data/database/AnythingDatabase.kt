package net.anything.data.database

import net.anything.data.dao.ItemDao

interface AnythingDatabase {

    val dao: ItemDao
}