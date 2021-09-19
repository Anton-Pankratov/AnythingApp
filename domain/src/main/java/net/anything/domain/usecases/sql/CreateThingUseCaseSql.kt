package net.anything.domain.usecases.sql

import net.anything.domain.entity.ShowThingEntity

class CreateThingUseCaseSql : BaseUseCaseSql() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.createThing(thing)
}