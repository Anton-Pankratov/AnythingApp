package net.anything.ui.dialog.create

import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.SaveThingUseCaseRoom
import net.anything.domain.usecases.sql.SaveThingUseCaseSql
import net.anything.ui.dialog.base.BaseDialogViewModel
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode._changeDbModeEvent
import net.anything.utils.dbMode.currentUseDb

class CreateThingViewModel : BaseDialogViewModel() {

    private val saveUseCaseRoom: SaveThingUseCaseRoom by locateLazy()
    private val saveUseCaseSql: SaveThingUseCaseSql by locateLazy()

    fun saveNewThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) {
                when (currentUseDb) {
                    DatabaseMode.ROOM -> saveUseCaseRoom.invoke(thing)
                    DatabaseMode.NATIVE -> {
                        saveUseCaseSql.invoke(thing)
                        _changeDbModeEvent.tryEmit(true)
                    }
                }
            }
        }
    }
}