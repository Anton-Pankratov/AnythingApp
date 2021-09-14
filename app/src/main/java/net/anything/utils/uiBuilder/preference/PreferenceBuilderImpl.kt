package net.anything.utils.uiBuilder.preference

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceScreen
import net.anything.anythingapp.R

class PreferenceBuilderImpl : PreferenceBuilder {

    private val tagFirst = Signs.SIGN_ONE.tag
    private val tagSecond = Signs.SIGN_TWO.tag
    private val tagThird = Signs.SIGN_THREE.tag
    private val tagCategory = Signs.FIELD_CATEGORY.tag
    private val tagName = Signs.FIELD_NAME.tag

    override fun PreferenceScreen.formScreen(context: Context) {
        with(context) {
            addPreferences(this, resources.getString(R.string.filter_categories), tagCategory)
            addPreferences(this, resources.getString(R.string.filter_names), tagName)
        }
    }

    override fun PreferenceScreen.addPreferences(context: Context, fieldTag: String, title: String) {
        with(context) {
            PreferencesCategory(title, fieldTag).apply {
                this@addPreferences.addPreference(this)
                add(
                    listOf(
                        Preference(fieldTag, sign(tagFirst)),
                        Preference(fieldTag, sign(tagSecond)),
                        Preference(fieldTag, sign(tagThird))
                    )
                )
            }
        }
    }

    override fun PreferenceCategory.add(preferences: List<Preference>) {
        preferences.forEach { addPreference(it) }
    }

    override fun Context.PreferencesCategory(fieldTag: String, name: String, ): PreferenceCategory {
        return PreferenceCategory(this).apply {
            title = name
        }
    }

    override fun Context.Preference(fieldTag: String, number: String, ): Preference {
        return Preference(this).apply {
            icon = signDrawable(number)
            title = number
           /* onPreferenceClickListener = Preference.OnPreferenceClickListener {

            }*/
        }
    }

    private fun Context.signDrawable(number: String): Drawable? {
        return when {
            number.contains(tagFirst) -> Signs.SIGN_ONE.res
            number.contains(tagSecond) -> Signs.SIGN_TWO.res
            else -> Signs.SIGN_THREE.res
        }?.let { tag ->
            ContextCompat.getDrawable(this, tag)
        }
    }

    private fun Context.sign(number: String): String {
        return String.format(resources.getString(R.string.filter_sign), number)
    }
}