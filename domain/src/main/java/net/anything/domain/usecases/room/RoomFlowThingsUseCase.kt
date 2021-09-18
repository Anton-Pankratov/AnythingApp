package net.anything.domain.usecases.room

class RoomFlowThingsUseCase : RoomBaseUseCase() {

    operator fun invoke() = repository.thingsFlow
}