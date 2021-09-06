package net.anything.data.di.di

import net.anything.data.database.AnythingDatabase
import net.anything.data.database.AnythingDatabaseImpl
import net.anything.data.repository.AnythingRepositoryImpl
import net.anything.domain.di.ServiceLocator
import net.anything.domain.di.locate
import net.anything.domain.repository.AnythingRepository

fun RegisterDataModule() {
    ServiceLocator.register<AnythingDatabase>(
        AnythingDatabaseImpl.build(
            locate()
        )
    )
    ServiceLocator.register<AnythingRepository>(
        AnythingRepositoryImpl(locate())
    )
}