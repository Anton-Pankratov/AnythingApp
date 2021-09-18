package net.anything.ui.dialog.update

import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.RoomUpdateThingUseCase
import net.anything.ui.dialog.base.BaseDialogViewModel

class UpdateThingViewModel : BaseDialogViewModel() {

    private val updateUseCaseUpdateThingUseCase: RoomUpdateThingUseCase by locateLazy()

    fun updateThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) updateUseCaseUpdateThingUseCase.invoke(thing)
        }
    }
}