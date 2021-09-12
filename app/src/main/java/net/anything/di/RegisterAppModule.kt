package net.anything.di

import android.content.Context
import net.anything.domain.di.ServiceLocator
import net.anything.domain.di.locate
import net.anything.utils.transactions.Transactor
import net.anything.utils.transactions.TransactorImpl
import net.anything.utils.uiBuilder.constraints.ConstraintsMaker
import net.anything.utils.uiBuilder.constraints.ConstraintsMakerImpl
import net.anything.utils.uiBuilder.screen.ScreenBuilder
import net.anything.utils.uiBuilder.screen.ScreenBuilderImpl
import net.anything.utils.uiBuilder.view.ViewGenerator
import net.anything.utils.uiBuilder.view.ViewGeneratorImpl

fun RegisterAppModule(context: Context) {

    ServiceLocator.apply {
        register(context)
        register<Transactor>(TransactorImpl())
        register<ConstraintsMaker>(ConstraintsMakerImpl())
        register<ViewGenerator>(ViewGeneratorImpl(locate()))
        register<ScreenBuilder>(ScreenBuilderImpl(locate(), locate()))
    }
}