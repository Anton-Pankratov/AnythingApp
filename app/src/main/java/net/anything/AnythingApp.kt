package net.anything

import android.app.Application
import android.content.Context
import net.anything.anythingapp.R
import net.anything.data.di.di.RegisterDataModule
import net.anything.di.RegisterAppModule
import net.anything.ui.MainActivity

class AnythingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setApplicationTheme()
        registerServiceLocator(this)
    }

    /**
     * This function needs to use MaterialToolbar.
     * Without it:
     * @exception IllegalArgumentException
     */
    private fun setApplicationTheme() {
        setTheme(R.style.Theme_AnythingApp)
    }

    private fun registerServiceLocator(context: Context) {
        RegisterAppModule(context)
        RegisterDataModule()
    }
}