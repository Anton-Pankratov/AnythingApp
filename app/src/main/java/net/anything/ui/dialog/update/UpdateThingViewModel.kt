package net.anything.ui.dialog.update

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.UpdateThingUseCase
import net.anything.ui.base.BaseViewModel
import net.anything.ui.dialog.base.BaseDialogViewModel

class UpdateThingViewModel : BaseDialogViewModel() {

    private val updateUseCase: UpdateThingUseCase by locateLazy()

    fun updateThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) updateUseCase.invoke(thing)
        }
    }
}