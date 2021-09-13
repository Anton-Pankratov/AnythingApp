package net.anything.di

import android.content.Context
import net.anything.domain.di.ServiceLocator
import net.anything.domain.di.locate
import net.anything.utils.transactions.Transactor
import net.anything.utils.transactions.TransactorImpl
import net.anything.utils.uiBuilder.constraints.ConstraintsMaker
import net.anything.utils.uiBuilder.constraints.ConstraintsMakerImpl
import net.anything.utils.uiBuilder.preference.PreferenceBuilder
import net.anything.utils.uiBuilder.preference.PreferenceBuilderImpl
import net.anything.utils.uiBuilder.screenBuilder.ScreenBuilder
import net.anything.utils.uiBuilder.screenBuilder.ScreenBuilderImpl
import net.anything.utils.uiBuilder.viewGenerator.ViewGenerator
import net.anything.utils.uiBuilder.viewGenerator.ViewGeneratorImpl

fun RegisterAppModule(context: Context) {

    ServiceLocator.apply {
        register(context)
        register<Transactor>(TransactorImpl())
        register<ConstraintsMaker>(ConstraintsMakerImpl())
        register<ViewGenerator>(ViewGeneratorImpl(locate()))
        register<ScreenBuilder>(ScreenBuilderImpl(locate(), locate()))
        register<PreferenceBuilder>(PreferenceBuilderImpl())
    }
}