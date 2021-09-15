package net.anything.ui.things

import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.DeleteThingUseCase
import net.anything.domain.usecases.FlowThingsUseCase
import net.anything.ui.base.BaseViewModel

class ThingsViewModel : BaseViewModel() {

    private val flowUseCase: FlowThingsUseCase by locateLazy()
    private val deleteUseCase: DeleteThingUseCase by locateLazy()

    val thingsFlow = flowUseCase.invoke()

    fun deleteThing(thing: ShowThingEntity) {
        scope.launch {
            deleteUseCase.invoke(thing)
        }
    }
}