package net.anything.domain.usecases.room

import net.anything.domain.entity.ShowThingEntity

class RoomSaveThingUseCase : RoomBaseUseCase() {

    suspend operator fun invoke(thing: ShowThingEntity) = repository.saveThing(thing)
}