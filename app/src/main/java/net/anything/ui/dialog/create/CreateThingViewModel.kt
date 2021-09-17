package net.anything.ui.dialog.create

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.SaveThingUseCase
import net.anything.domain.usecases.UpdateThingUseCase
import net.anything.ui.base.BaseViewModel
import net.anything.ui.dialog.base.BaseDialogViewModel

class CreateThingViewModel : BaseDialogViewModel() {

    private val saveUseCase: SaveThingUseCase by locateLazy()

    fun saveNewThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) saveUseCase.invoke(thing)
        }
    }
}