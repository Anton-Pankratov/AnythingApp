package net.anything.di

import android.content.Context
import net.anything.domain.di.ServiceLocator
import net.anything.domain.di.locate
import net.anything.utils.screenBuilder.ScreenBuilder
import net.anything.utils.screenBuilder.ScreenBuilderImpl

fun RegisterAppModule(context: Context) {

    ServiceLocator.register(context)
    ServiceLocator.register<ScreenBuilder>(ScreenBuilderImpl(locate()))

}