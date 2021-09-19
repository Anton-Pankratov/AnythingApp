package net.anything.utils.dbMode

fun interface OnChangeDbModeListener {

    fun onChange(mode: DatabaseMode)
}