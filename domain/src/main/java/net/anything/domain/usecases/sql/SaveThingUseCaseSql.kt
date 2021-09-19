package net.anything.domain.usecases.sql

import net.anything.domain.entity.ShowThingEntity

class SaveThingUseCaseSql : BaseUseCaseSql() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.saveThing(thing)
}