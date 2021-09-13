package net.anything.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.transactions.Screens

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val container by lazy {
        viewModel.screenBuilder.buildFragmentContainer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(container)
        openThingsScreen()
    }

    val transactionsListener = OnTransaction { screen ->
        when (screen) {
            Screens.THINGS -> openThingsScreen()
            Screens.FILTER -> openFilterScreen()
            Screens.NEW_THING -> showCreatingNewThingDialog()
        }
    }

    private fun openThingsScreen() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openThingsScreen(container as FrameLayout)
        }
    }

    private fun openFilterScreen() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openPreferencesScreen(container as FrameLayout)
        }
    }

    private fun showCreatingNewThingDialog() {
        viewModel.transactor.apply {
            supportFragmentManager
                .showCreatingNewThingScreen()
        }
    }
}