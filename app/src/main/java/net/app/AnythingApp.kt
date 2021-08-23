package net.app

import android.app.Application
import net.domain.di.ServiceLocatorRegister

class AnythingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocatorRegister(this)
    }
}