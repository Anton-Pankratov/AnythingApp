package net.anything.ui.create

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.SaveThingUseCase
import net.anything.ui.base.BaseViewModel

class CreateThingViewModel : BaseViewModel() {

    private val saveUseCase: SaveThingUseCase by locateLazy()

    fun saveNewThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) saveUseCase.invoke(thing)
        }
    }
}