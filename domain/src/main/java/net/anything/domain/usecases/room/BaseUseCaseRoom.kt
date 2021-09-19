package net.anything.domain.usecases.room

import net.anything.domain.di.locateLazy
import net.anything.domain.repository.AnythingRepositoryRoom

abstract class BaseUseCaseRoom {

    protected val repository: AnythingRepositoryRoom by locateLazy()
}