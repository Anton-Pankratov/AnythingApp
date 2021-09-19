package net.anything.domain.usecases.sql

class DeleteAllThingsUseCaseSql : BaseUseCaseSql() {

    suspend operator fun invoke() = repository.deleteAllThings()
}