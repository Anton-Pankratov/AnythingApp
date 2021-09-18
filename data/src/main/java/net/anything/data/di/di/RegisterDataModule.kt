package net.anything.data.di.di

import net.anything.data.databases.converter.Converter
import net.anything.data.databases.room.AnythingDatabase
import net.anything.data.databases.room.AnythingDatabaseImpl
import net.anything.data.databases.converter.ConverterImpl
import net.anything.data.databases.sql.AnythingDbHandler
import net.anything.data.databases.sql.AnythingDbHandlerImpl
import net.anything.data.mapper.ThingMapper
import net.anything.data.repository.AnyThingDataSource
import net.anything.data.repository.AnythingDataSourceImpl
import net.anything.data.repository.AnythingRepositoryImpl
import net.anything.domain.di.ServiceLocator
import net.anything.domain.di.locate
import net.anything.domain.repository.AnythingRepository

fun RegisterDataModule() {
    ServiceLocator.apply {
        register(ThingMapper())
        register<Converter>(ConverterImpl())

        /** Room */
        register<AnythingDatabase>(AnythingDatabaseImpl.build(locate()))
        register<AnythingRepository>(AnythingRepositoryImpl())
        register<AnyThingDataSource>(AnythingDataSourceImpl(locate(), locate()))

        /** SqlOpenHelper */
        register<AnythingDbHandler>(AnythingDbHandlerImpl(locate()))
    }
}