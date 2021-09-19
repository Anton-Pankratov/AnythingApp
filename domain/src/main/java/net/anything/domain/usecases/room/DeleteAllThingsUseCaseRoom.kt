package net.anything.domain.usecases.room

class DeleteAllThingsUseCaseRoom : BaseUseCaseRoom() {

    suspend operator fun invoke() = repository.deleteAllThings()
}