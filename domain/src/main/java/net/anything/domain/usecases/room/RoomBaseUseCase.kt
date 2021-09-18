package net.anything.domain.usecases.room

import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.repository.AnythingRepository

abstract class RoomBaseUseCase {

    protected val repository: AnythingRepository by locateLazy()
}