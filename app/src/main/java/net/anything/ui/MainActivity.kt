package net.anything.ui

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import net.anything.ui.dialog.create.CreateThingFragment
import net.anything.ui.dialog.update.UpdateThingFragment
import net.anything.ui.filter.FilterPreferenceFragment
import net.anything.ui.filter.OnFilterPreferenceClickListener
import net.anything.ui.things.ThingsFragment
import net.anything.utils.transactions.OnTransaction
import net.anything.utils.transactions.Screens

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val container by lazy {
        viewModel.screenBuilder.buildFragmentContainer()
    }

    private val thingsScreen = ThingsFragment.getInstance()
    private val filterScreen = FilterPreferenceFragment.getInstance()
    private val createThingDialog get() = CreateThingFragment.instance()
    private val updateThingDialog get() = UpdateThingFragment.instance()

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

    val transactionsListener = OnTransaction { screen, selectedThing ->
        when (screen) {
            Screens.THINGS -> openThingsScreen()
            Screens.FILTER -> openFilterScreen()
            Screens.NEW_THING -> showCreateThingDialog()
            Screens.UPDATE_THING -> showUpdateThingDialog(selectedThing)
        }
    }

    fun filterListener(): OnFilterPreferenceClickListener {
        onBackPressed()
        return thingsScreen.filterListener
    }

    private fun openThingsScreen() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openScreen(thingsScreen, container as FrameLayout)
        }
    }

    private fun openFilterScreen() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openScreen(filterScreen, container as FrameLayout)
        }
    }

    private fun showCreateThingDialog() {
        viewModel.transactor.apply {
            supportFragmentManager
                .openCreatingThingDialog(createThingDialog)
        }
    }

    private fun showUpdateThingDialog(thing: Bundle?) {
        viewModel.transactor.apply {
            supportFragmentManager
                .openUpdatingThingDialog(
                    updateThingDialog.apply {
                        arguments = thing
                    }
                )
        }
    }

    private fun startActivityInNewTask() {
        Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }
}