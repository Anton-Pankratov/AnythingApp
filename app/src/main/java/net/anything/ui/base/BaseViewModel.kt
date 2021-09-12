package net.anything.ui.base

import androidx.lifecycle.ViewModel
import net.anything.domain.di.locateLazy
import net.anything.utils.transactions.Transactor
import net.anything.utils.uiBuilder.screen.ScreenBuilder

abstract class BaseViewModel : ViewModel() {

    val transactor: Transactor by locateLazy()

    val screenBuilder: ScreenBuilder by locateLazy()
}