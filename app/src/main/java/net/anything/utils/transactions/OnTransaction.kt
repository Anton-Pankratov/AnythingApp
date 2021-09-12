package net.anything.utils.transactions

fun interface OnTransaction {

    fun begin(screen: Screens)
}