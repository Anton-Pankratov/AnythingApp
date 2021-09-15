package net.anything.domain.di

import net.anything.domain.usecases.DeleteThingUseCase
import net.anything.domain.usecases.FlowThingsUseCase
import net.anything.domain.usecases.SaveThingUseCase
import net.anything.domain.usecases.UpdateThingUseCase

fun RegisterDomainModule() {
    ServiceLocator.apply {
        register(FlowThingsUseCase())
        register(SaveThingUseCase())
        register(UpdateThingUseCase())
        register(DeleteThingUseCase())
    }
}