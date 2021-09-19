package net.anything.domain.usecases.room

import net.anything.domain.entity.ShowThingEntity

class DeleteThingUseCaseRoom : BaseUseCaseRoom() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.deleteThing(thing)
}