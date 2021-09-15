package net.anything.domain.usecases

class FlowThingsUseCase : BaseUseCase() {

    operator fun invoke() = repository.thingsFlow
}