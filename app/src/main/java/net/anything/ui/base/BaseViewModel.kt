package net.anything.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import net.anything.domain.di.locateLazy
import net.anything.utils.transactions.Transactor
import net.anything.utils.uiBuilder.screenBuilder.ScreenBuilder

abstract class BaseViewModel : ViewModel() {

    val transactor: Transactor by locateLazy()
    val screenBuilder: ScreenBuilder by locateLazy()

    protected val scope = CoroutineScope(Dispatchers.IO)
}