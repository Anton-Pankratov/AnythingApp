package net.anything.ui.filter

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class FilterFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        Preference(context).apply {
            title = "TEST BUTTON"
        }

        preferenceScreen = screen
    }

    companion object {
        fun getInstance() = FilterFragment()
    }
}