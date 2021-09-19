package net.anything.domain.usecases.sql

import net.anything.domain.di.locateLazy
import net.anything.domain.repository.AnythingRepositorySql

abstract class BaseUseCaseSql {

    protected val repository: AnythingRepositorySql by locateLazy()
}