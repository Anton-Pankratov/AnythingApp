package net.anything.domain.usecases

import net.anything.domain.entity.ShowThingEntity

class UpdateThingUseCase : BaseUseCase() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.updateThing(thing)
}