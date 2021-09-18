package net.anything.domain.usecases.room

import net.anything.domain.entity.ShowThingEntity

class RoomDeleteThingUseCase : RoomBaseUseCase() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.deleteThing(thing)
}