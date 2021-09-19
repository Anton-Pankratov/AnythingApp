package net.anything.domain.usecases.room

class FlowThingsUseCaseRoom : BaseUseCaseRoom() {

    operator fun invoke() = repository.thingsFlow
}