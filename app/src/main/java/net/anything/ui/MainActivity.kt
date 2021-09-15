package net.anything.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceScreen
import net.anything.data.di.di.RegisterDataModule
import net.anything.di.RegisterAppModule
import net.anything.domain.di.RegisterDomainModule
import net.anything.ui.create.CreateThingFragment
import net.anything.ui.filter.FilterFragment
import net.anything.ui.things.ThingsFragment
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

    override fun onBackPressed() {
        supportFragmentManager.findFragmentById(container.id)
            .apply {
                if (this is ThingsFragment)
                    startActivityInNewTask()
                else super.onBackPressed()
            }
    }

    val transactionsListener = OnTransaction { screen ->
        when (screen) {
            Screens.THINGS -> openThingsScreen()
            Screens.FILTER -> openFilterScreen()
            Screens.NEW_THING -> showCreatingNewThingDialog()
        }
    }

    private val thingsScreen = ThingsFragment.getInstance()
    private val preferencesScreen = FilterFragment.getInstance()
    private val addNewThingDialog = CreateThingFragment.getInstance()

    private fun openThingsScreen() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openScreen(thingsScreen, container as FrameLayout)
        }
    }

    private fun openFilterScreen() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openScreen(preferencesScreen, container as FrameLayout)
        }
    }

    private fun showCreatingNewThingDialog() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openDialog(addNewThingDialog)
        }
    }

    private fun startActivityInNewTask() {
        Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }
/*
    private fun listenAddNewThing() {
        addNewThingDialog.onAddNewThingClick {

        }
    }

    private fun listenSelectedFilter() {
        preferencesScreen.
    }*/
}