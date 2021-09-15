package net.anything.domain.usecases

import net.anything.domain.entity.ShowThingEntity

class DeleteThingUseCase : BaseUseCase() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.deleteThing(thing)
}