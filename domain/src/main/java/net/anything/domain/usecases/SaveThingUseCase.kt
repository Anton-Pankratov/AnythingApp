package net.anything.domain.usecases

import net.anything.domain.entity.ShowThingEntity

class SaveThingUseCase : BaseUseCase() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.saveThing(thing)
}