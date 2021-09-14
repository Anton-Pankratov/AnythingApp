package net.anything.utils.uiBuilder.preference

import android.content.Context
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceScreen

interface PreferenceBuilder {

    fun PreferenceScreen.formScreen(context: Context)

    fun PreferenceScreen.addPreferences(context: Context, fieldTag: String, title: String, )

    fun PreferenceCategory.add(preferences: List<Preference>)

    fun Context.PreferencesCategory(fieldTag: String, name: String, ): PreferenceCategory

    fun Context.Preference(fieldTag: String, number: String, ): Preference
}