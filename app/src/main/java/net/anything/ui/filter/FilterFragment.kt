package net.anything.ui.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import net.anything.ui.MainActivity
import net.anything.utils.getActivity

class FilterFragment : PreferenceFragmentCompat() {

    private val viewModel: FilterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        screen.addPreference(Preference(context).apply {
            title = "TEST BUTTON"
        })

        preferenceScreen = screen
    }

    companion object {
        fun getInstance() = FilterFragment()
    }
}