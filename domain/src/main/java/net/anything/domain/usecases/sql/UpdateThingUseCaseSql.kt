package net.anything.domain.usecases.sql

import net.anything.domain.entity.ShowThingEntity

class UpdateThingUseCaseSql : BaseUseCaseSql() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.updateThing(thing)
}