package net.anything.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.anything.domain.di.locateLazy
import net.anything.domain.entity.ShowThingEntity
import net.anything.domain.usecases.DeleteThingUseCase
import net.anything.domain.usecases.FlowThingsUseCase
import net.anything.domain.usecases.SaveThingUseCase
import net.anything.domain.usecases.UpdateThingUseCase
import net.anything.utils.transactions.Transactor
import net.anything.utils.uiBuilder.screenBuilder.ScreenBuilder

abstract class BaseViewModel : ViewModel() {

    val transactor: Transactor by locateLazy()
    val screenBuilder: ScreenBuilder by locateLazy()

    protected val scope = CoroutineScope(Dispatchers.IO)
}