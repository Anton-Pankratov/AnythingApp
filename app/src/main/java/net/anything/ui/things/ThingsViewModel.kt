package net.anything.ui.things

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.RoomDeleteThingUseCase
import net.anything.domain.usecases.room.RoomFlowThingsUseCase
import net.anything.ui.base.BaseViewModel
import net.anything.utils.uiBuilder.preference.SignKeys

class ThingsViewModel : BaseViewModel() {

    private val flowUseCaseFlowThingsUseCase: RoomFlowThingsUseCase by locateLazy()
    private val deleteUseCaseDeleteThingUseCase: RoomDeleteThingUseCase by locateLazy()

    val thingsFlow = flowUseCaseFlowThingsUseCase.invoke()

    fun deleteThing(thing: ShowThingEntity) {
        scope.launch {
            deleteUseCaseDeleteThingUseCase.invoke(thing)
        }
    }

    fun sortedThingsFlow(filter: String) =
        flowUseCaseFlowThingsUseCase.invoke()?.map { things ->
            things.sortedBy {
                when (filter) {
                    SignKeys.CATEGORY_ONE.key -> it.sign1?.header
                    SignKeys.CATEGORY_TWO.key -> it.sign2?.header
                    SignKeys.CATEGORY_THREE.key -> it.sign3?.header
                    SignKeys.NAME_ONE.key -> it.sign1?.value
                    SignKeys.NAME_TWO.key -> it.sign2?.value
                    else -> it.sign3?.value
                }
            }
        }
}