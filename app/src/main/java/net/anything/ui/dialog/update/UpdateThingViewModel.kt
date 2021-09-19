package net.anything.ui.dialog.update

import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.UpdateThingUseCaseRoom
import net.anything.domain.usecases.sql.UpdateThingUseCaseSql
import net.anything.ui.base.BaseViewModel
import net.anything.ui.dialog.base.BaseDialogFragment
import net.anything.ui.dialog.base.BaseDialogViewModel
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode._changeDbModeEvent
import net.anything.utils.dbMode.currentDbMode

class UpdateThingViewModel : BaseDialogViewModel() {

    private val updateUseCaseRoom: UpdateThingUseCaseRoom by locateLazy()
    private val updateUseCaseSql: UpdateThingUseCaseSql by locateLazy()

    fun updateThing(thing: ShowThingEntity?) {
        scope.launch {
            if (thing != null) {
                when (currentDbMode) {
                    DatabaseMode.ROOM -> {
                        updateUseCaseRoom.invoke(thing)
                    }
                    DatabaseMode.NATIVE -> {
                        updateUseCaseSql.invoke(thing)
                        _changeDbModeEvent.tryEmit(true)
                    }
                }
            }
        }
    }
}