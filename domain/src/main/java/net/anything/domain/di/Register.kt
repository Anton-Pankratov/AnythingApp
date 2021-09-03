package net.anything.domain.di

import android.content.Context
import net.anything.data.database.AnythingDatabase
import net.anything.data.database.AnythingDatabaseImpl
import net.anything.data.repository.AnythingRepositoryImpl
import net.anything.domain.repository.AnythingRepository

fun ServiceLocatorRegister(context: Context) {
    context.apply {
        ServiceLocator.register(context)
        ServiceLocator.register<net.anything.data.database.AnythingDatabase>(
            net.anything.data.database.AnythingDatabaseImpl.build(
                locate()
            )
        )
        ServiceLocator.register<AnythingRepository>(
            net.anything.data.repository.AnythingRepositoryImpl(
                locate()
            )
        )
    }
}