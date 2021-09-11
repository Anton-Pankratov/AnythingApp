package net.anything.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.anything.domain.di.locateLazy
import net.anything.entity.ShowSign
import net.anything.entity.ShowThingEntity
import net.anything.utils.uiBuilder.screen.ScreenBuilder
import net.anything.utils.uiBuilder.view.ViewGenerator

class MainActivity : AppCompatActivity() {

    private val screenBuilder: ScreenBuilder by locateLazy()

    private val viewGenerator: ViewGenerator by locateLazy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(screenBuilder.buildContentView())

        viewGenerator.thingsView.submit(listOf(
            ShowThingEntity(
                id = 1,
                sign1 = ShowSign(id = 1, header = "aaa", value = "bbb"),
                sign2 = ShowSign(id = 2, header = "ccc", value = "ddd"),
                sign3 = ShowSign(id = 3, header = "eee", value = "fff")
            )
        ))
    }
}