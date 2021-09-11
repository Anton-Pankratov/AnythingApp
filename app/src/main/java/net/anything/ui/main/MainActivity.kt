package net.anything.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.anything.domain.di.locateLazy
import net.anything.utils.uiBuilder.screen.ScreenBuilder
import net.anything.utils.uiBuilder.view.ViewGenerator

class MainActivity : AppCompatActivity() {

    private val screenBuilder: ScreenBuilder by locateLazy()

    private val viewGenerator: ViewGenerator by locateLazy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(screenBuilder.buildContentView(supportFragmentManager))
    }
}