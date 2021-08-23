package net.domain.di

import android.content.Context
import net.data.database.AnythingDatabase
import net.data.database.AnythingDatabaseImpl
import net.data.repository.AnythingRepositoryImpl
import net.domain.repository.AnythingRepository

fun ServiceLocatorRegister(context: Context) {
    context.apply {
        ServiceLocator.register(context)
        ServiceLocator.register<AnythingDatabase>(AnythingDatabaseImpl.build(locate()))
        ServiceLocator.register<AnythingRepository>(AnythingRepositoryImpl(locate()))
    }
}