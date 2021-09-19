package net.anything.data.di.di

import net.anything.data.databases.converter.Converter
import net.anything.data.databases.room.RoomHelper
import net.anything.data.databases.room.AnythingRoomDatabase
import net.anything.data.databases.converter.ConverterImpl
import net.anything.data.databases.sql.crud.ThingsCrud
import net.anything.data.databases.sql.AnythingSqlDatabase
import net.anything.data.databases.sql.crud.ThingsCrudImpl
import net.anything.data.mapper.ThingMapper
import net.anything.data.source.room.AnythingDataSourceRoom
import net.anything.data.source.room.AnythingDataSourceImplRoom
import net.anything.data.repository.AnythingRepositoryImplRoom
import net.anything.data.repository.AnythingRepositoryImplSql
import net.anything.data.source.sql.AnythingDataSourceImplSql
import net.anything.data.source.sql.AnythingDataSourceSql
import net.anything.domain.di.ServiceLocator
import net.anything.domain.di.locate
import net.anything.domain.repository.AnythingRepositoryRoom
import net.anything.domain.repository.AnythingRepositorySql

fun RegisterDataModule() {
    ServiceLocator.apply {
        register(ThingMapper())
        register<Converter>(ConverterImpl())

        /** Room */
        register<RoomHelper>(AnythingRoomDatabase.build(locate()))
        register<AnythingRepositoryRoom>(AnythingRepositoryImplRoom())
        register<AnythingDataSourceRoom>(AnythingDataSourceImplRoom(locate(), locate()))

        /** SqlOpenHelper */
        register(AnythingSqlDatabase(locate()))
        register<ThingsCrud>(ThingsCrudImpl(locate(), locate()))
        register<AnythingRepositorySql>(AnythingRepositoryImplSql())
        register<AnythingDataSourceSql>(AnythingDataSourceImplSql(locate(), locate()))
    }
}