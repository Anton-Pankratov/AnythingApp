package net.anything.utils.transactions

import android.os.Bundle

fun interface OnTransaction {

    fun begin(screen: Screens, arguments: Bundle?)
}