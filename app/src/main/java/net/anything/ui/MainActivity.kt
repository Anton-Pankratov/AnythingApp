package net.anything.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.anything.domain.di.locateLazy
import net.anything.utils.screenBuilder.ScreenBuilder

class MainActivity : AppCompatActivity() {

    private val screenBuilder: ScreenBuilder by locateLazy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(screenBuilder.buildContentView())
    }
}