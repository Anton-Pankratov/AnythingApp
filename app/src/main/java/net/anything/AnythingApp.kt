package net.anything

import android.app.Application
import net.anything.domain.di.ServiceLocatorRegister

class AnythingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocatorRegister(this)
    }
}