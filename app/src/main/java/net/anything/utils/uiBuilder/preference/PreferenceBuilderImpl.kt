package net.anything.utils.uiBuilder.preference

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceScreen

class PreferenceBuilderImpl : PreferenceBuilder {

    private val tagCategory = Signs.FIELD_CATEGORY.tag
    private val tagName = Signs.FIELD_NAME.tag

    override fun PreferenceScreen.formScreen(context: Context) {
        with(context) {
            addPreferences(this, tagCategory)
            addPreferences(this, tagName)
        }
    }

    override fun PreferenceScreen.addPreferences(context: Context, field: String) {
        with(context) {
            PreferencesCategory(field).apply {
                this@addPreferences.addPreference(this)
                add(
                    listOf(
                        Preference(SignPreference(field, Signs.SIGN_ONE.tag)),
                        Preference(SignPreference(field, Signs.SIGN_TWO.tag)),
                        Preference(SignPreference(field, Signs.SIGN_THREE.tag))
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

    override fun Context.Preference(tag: SignPreference): Preference {
        return Preference(this).apply {
            with(tag) {
                key = "$field $sign"
                icon = signDrawable(sign)
                title = sign
            }
        }
    }

    private fun Context.signDrawable(number: String): Drawable? {
        return when {
            number.contains(Signs.SIGN_ONE.tag) -> Signs.SIGN_ONE.res
            number.contains(Signs.SIGN_TWO.tag) -> Signs.SIGN_TWO.res
            else -> Signs.SIGN_THREE.res
        }?.let { tag ->
            ContextCompat.getDrawable(this, tag)
        }
    }
}