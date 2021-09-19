package net.anything.domain.usecases.room

import net.anything.domain.entity.ShowThingEntity

class UpdateThingUseCaseRoom : BaseUseCaseRoom() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.updateThing(thing)
}