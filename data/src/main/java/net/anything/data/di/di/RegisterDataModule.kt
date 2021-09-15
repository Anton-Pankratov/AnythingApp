package net.anything.data.di.di

import net.anything.data.database.AnythingDatabase
import net.anything.data.database.AnythingDatabaseImpl
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
        register<AnythingDatabase>(AnythingDatabaseImpl.build(locate()))
        register<AnythingRepository>(AnythingRepositoryImpl())
        register<AnyThingDataSource>(AnythingDataSourceImpl(locate(), locate()))
    }
}