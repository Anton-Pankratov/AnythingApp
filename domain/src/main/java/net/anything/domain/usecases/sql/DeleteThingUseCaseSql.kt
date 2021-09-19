package net.anything.domain.usecases.sql

import net.anything.domain.entity.ShowThingEntity

class DeleteThingUseCaseSql : BaseUseCaseSql() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.deleteThing(thing)
}