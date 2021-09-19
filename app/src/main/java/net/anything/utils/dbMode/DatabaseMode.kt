package net.anything.utils.dbMode

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

val _changeDbModeEvent = MutableStateFlow(false)
val changeDbModeEvent: StateFlow<Boolean> = _changeDbModeEvent

var currentUseDb = DatabaseMode.ROOM

enum class DatabaseMode {
    ROOM, NATIVE
}