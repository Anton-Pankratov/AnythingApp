package net.anything.ui.filter

import android.app.Activity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import net.anything.anythingapp.R
import net.anything.domain.di.locateLazy
import net.anything.utils.getMainActivity
import net.anything.utils.uiBuilder.preference.PreferenceBuilder

class FilterFragment : PreferenceFragmentCompat() {

    private val preferencesBuilder: PreferenceBuilder by locateLazy()

    override fun onResume() {
        super.onResume()
        activity?.configureActionBar()
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