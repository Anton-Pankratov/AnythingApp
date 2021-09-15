package net.anything.data.database

import net.anything.data.dao.ThingsDao

interface AnythingDatabase {

    val dao: ThingsDao
}