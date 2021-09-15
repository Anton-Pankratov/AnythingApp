package net.anything.domain.usecases

import net.anything.domain.di.locateLazy
import net.anything.domain.repository.AnythingRepository

abstract class BaseUseCase {

    protected val repository: AnythingRepository by locateLazy()
}