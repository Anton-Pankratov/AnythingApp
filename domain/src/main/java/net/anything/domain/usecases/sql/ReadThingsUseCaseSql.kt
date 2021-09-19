package net.anything.domain.usecases.sql

class ReadThingsUseCaseSql : BaseUseCaseSql() {

    suspend operator fun invoke() = repository.readThings()
}