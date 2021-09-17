package net.anything.ui.things

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.DeleteThingUseCase
import net.anything.domain.usecases.FlowThingsUseCase
import net.anything.ui.base.BaseViewModel
import net.anything.utils.uiBuilder.preference.SignKeys

class ThingsViewModel : BaseViewModel() {

    private val flowUseCase: FlowThingsUseCase by locateLazy()
    private val deleteUseCase: DeleteThingUseCase by locateLazy()

    val thingsFlow = flowUseCase.invoke()

    fun deleteThing(thing: ShowThingEntity) {
        scope.launch {
            deleteUseCase.invoke(thing)
        }
    }

    fun sortedThingsFlow(filter: String) =
        flowUseCase.invoke().map { things ->
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