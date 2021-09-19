package net.anything.ui.things

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.room.DeleteAllThingsUseCaseRoom
import net.anything.domain.usecases.room.DeleteThingUseCaseRoom
import net.anything.domain.usecases.room.FlowThingsUseCaseRoom
import net.anything.domain.usecases.sql.DeleteAllThingsUseCaseSql
import net.anything.domain.usecases.sql.DeleteThingUseCaseSql
import net.anything.domain.usecases.sql.ReadThingsUseCaseSql
import net.anything.ui.base.BaseViewModel
import net.anything.utils.dbMode.DatabaseMode
import net.anything.utils.dbMode._changeDbModeEvent
import net.anything.utils.dbMode.currentDbMode
import net.anything.utils.uiBuilder.preference.SignKey

class ThingsViewModel : BaseViewModel() {

    private val flowUseCaseRoom: FlowThingsUseCaseRoom by locateLazy()
    private val deleteUseCaseRoom: DeleteThingUseCaseRoom by locateLazy()
    private val deleteAllUseCaseRoom: DeleteAllThingsUseCaseRoom by locateLazy()

    private val readUseCaseSql: ReadThingsUseCaseSql by locateLazy()
    private val deleteUseCaseSql: DeleteThingUseCaseSql by locateLazy()
    private val deleteAllUseCaseSql: DeleteAllThingsUseCaseSql by locateLazy()

    val thingsFlowRoom = flowUseCaseRoom.invoke()

    suspend fun thingsSql(): List<ShowThingEntity> {
        return readUseCaseSql.invoke()
    }

    fun deleteThing(thing: ShowThingEntity) {
        scope.launch {
            when (currentDbMode) {
                DatabaseMode.ROOM -> deleteUseCaseRoom.invoke(thing)
                DatabaseMode.NATIVE -> {
                    deleteUseCaseSql.invoke(thing)
                    _changeDbModeEvent.tryEmit(true)
                }
            }
        }
    }

    fun deleteAllThings() {
        scope.launch {
            when (currentDbMode) {
                DatabaseMode.ROOM -> deleteAllUseCaseRoom.invoke()
                DatabaseMode.NATIVE -> {
                    deleteAllUseCaseSql.invoke()
                    _changeDbModeEvent.tryEmit(true)
                }
            }
        }
    }

    fun sortedThingsFlowRoom(filter: String) =
        flowUseCaseRoom.invoke()?.map { things ->
            things.sortBy(filter)
        }

    suspend fun sortedThingsSql(filter: String) =
        readUseCaseSql.invoke().sortBy(filter)

    private fun List<ShowThingEntity>.sortBy(filter: String) =
        sortedBy {
            when (filter) {
                SignKey.CATEGORY_ONE.key -> it.sign1?.header
                SignKey.CATEGORY_TWO.key -> it.sign2?.header
                SignKey.CATEGORY_THREE.key -> it.sign3?.header
                SignKey.NAME_ONE.key -> it.sign1?.value
                SignKey.NAME_TWO.key -> it.sign2?.value
                else -> it.sign3?.value
            }
        }
}