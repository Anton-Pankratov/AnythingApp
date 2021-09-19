package net.anything.ui.dialog.create

import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.SaveThingUseCaseRoom
import net.anything.domain.usecases.sql.CreateThingUseCaseSql
import net.anything.ui.dialog.base.BaseDialogViewModel
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode._changeDbModeEvent
import net.anything.utils.dbMode.currentDbMode

class CreateThingViewModel : BaseDialogViewModel() {

    private val saveUseCaseRoom: SaveThingUseCaseRoom by locateLazy()
    private val saveUseCaseSql: CreateThingUseCaseSql by locateLazy()

    fun saveNewThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) {
                when (currentDbMode) {
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