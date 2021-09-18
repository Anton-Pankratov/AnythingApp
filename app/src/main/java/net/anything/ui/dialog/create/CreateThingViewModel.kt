package net.anything.ui.dialog.create

import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.RoomSaveThingUseCase
import net.anything.ui.dialog.base.BaseDialogViewModel

class CreateThingViewModel : BaseDialogViewModel() {

    private val saveUseCase: RoomSaveThingUseCase by locateLazy()

    fun saveNewThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) saveUseCase.invoke(thing)
        }
    }
}