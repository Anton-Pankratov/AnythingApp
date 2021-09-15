package net.anything.ui.filter

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import net.anything.anythingapp.R
import net.anything.domain.di.locateLazy
import net.anything.utils.getMainActivity
import net.anything.utils.uiBuilder.preference.PreferenceBuilder

class FilterFragment : PreferenceFragmentCompat() {

    private val viewModel: FilterViewModel by viewModels()

    private val preferencesBuilder: PreferenceBuilder by locateLazy()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        activity?.configureActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) activity?.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        with(preferenceManager) {
            preferencesBuilder.apply {
                createPreferenceScreen(context).apply {
                    formScreen(this@with.context)
                    this@FilterFragment.preferenceScreen = this
                }
            }
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {


        return super.onPreferenceTreeClick(preference)
    }

    private fun Activity.configureActionBar() {
        getMainActivity().supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.filter_action_bar_title)
        }
    }

    companion object {
        fun getInstance() = FilterFragment()
    }
}