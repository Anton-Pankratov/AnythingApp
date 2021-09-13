package net.anything.utils.uiBuilder.preference

import android.content.Context
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceScreen
import net.anything.anythingapp.R

class PreferenceBuilderImpl : PreferenceBuilder {

    override fun PreferenceScreen.formScreen(context: Context) {
        with(context) {
            addPreferences(this, resources.getString(R.string.filter_categories))
            addPreferences(this, resources.getString(R.string.filter_names))
        }
    }

    override fun PreferenceScreen.addPreferences(context: Context, title: String) {
        with(context) {
            PreferencesCategory(title).apply {
                this@addPreferences.addPreference(this)
                add(
                    listOf(
                        Preference(sign(1)),
                        Preference(sign(2)),
                        Preference(sign(3))
                    )
                )
            }
        }
    }

    override fun PreferenceCategory.add(preferences: List<Preference>) {
        preferences.forEach { addPreference(it) }
    }

    override fun Context.PreferencesCategory(name: String): PreferenceCategory {
        return PreferenceCategory(this).apply {
            title = name
        }
    }

    override fun Context.Preference(name: String): Preference {
        return Preference(this).apply {
            title = name
        }
    }

    private fun Context.sign(number: Int): String {
        return String.format(resources.getString(R.string.filter_sign), number)
    }
}