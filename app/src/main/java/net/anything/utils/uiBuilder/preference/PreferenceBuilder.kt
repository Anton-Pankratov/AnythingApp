package net.anything.utils.uiBuilder.preference

import android.content.Context
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceScreen

interface PreferenceBuilder {

    fun PreferenceScreen.formScreen(context: Context)

    fun PreferenceScreen.addPreferences(context: Context, field: String)

    fun PreferenceCategory.add(preferences: List<Preference>)

    fun Context.PreferencesCategory(name: String): PreferenceCategory

    fun Context.Preference(tag: SignPreference): Preference
}