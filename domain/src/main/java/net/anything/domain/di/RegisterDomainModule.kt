package net.anything.domain.di

import net.anything.domain.usecases.room.DeleteThingUseCaseRoom
import net.anything.domain.usecases.room.FlowThingsUseCaseRoom
import net.anything.domain.usecases.room.SaveThingUseCaseRoom
import net.anything.domain.usecases.room.UpdateThingUseCaseRoom
import net.anything.domain.usecases.sql.DeleteThingUseCaseSql
import net.anything.domain.usecases.sql.ReadThingsUseCaseSql
import net.anything.domain.usecases.sql.SaveThingUseCaseSql
import net.anything.domain.usecases.sql.UpdateThingUseCaseSql

fun RegisterDomainModule() {
    ServiceLocator.apply {

        /** Room */
        register(FlowThingsUseCaseRoom())
        register(SaveThingUseCaseRoom())
        register(UpdateThingUseCaseRoom())
        register(DeleteThingUseCaseRoom())

        /** SqlOpenHelper */
        register(ReadThingsUseCaseSql())
        register(SaveThingUseCaseSql())
        register(UpdateThingUseCaseSql())
        register(DeleteThingUseCaseSql())
    }
}