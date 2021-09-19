package net.anything.domain.di

import net.anything.domain.usecases.room.*
import net.anything.domain.usecases.sql.*

fun RegisterDomainModule() {
    ServiceLocator.apply {

        /** Room */
        register(FlowThingsUseCaseRoom())
        register(SaveThingUseCaseRoom())
        register(UpdateThingUseCaseRoom())
        register(DeleteThingUseCaseRoom())
        register(DeleteAllThingsUseCaseRoom())

        /** SqlOpenHelper */
        register(ReadThingsUseCaseSql())
        register(CreateThingUseCaseSql())
        register(UpdateThingUseCaseSql())
        register(DeleteThingUseCaseSql())
        register(DeleteAllThingsUseCaseSql())
    }
}