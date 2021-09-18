package net.anything.domain.di

import net.anything.domain.usecases.room.RoomDeleteThingUseCase
import net.anything.domain.usecases.room.RoomFlowThingsUseCase
import net.anything.domain.usecases.room.RoomSaveThingUseCase
import net.anything.domain.usecases.room.RoomUpdateThingUseCase

fun RegisterDomainModule() {
    ServiceLocator.apply {
        register(RoomFlowThingsUseCase())
        register(RoomSaveThingUseCase())
        register(RoomUpdateThingUseCase())
        register(RoomDeleteThingUseCase())
    }
}