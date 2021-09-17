package net.anything.utils.uiBuilder.preference

import androidx.annotation.DrawableRes
import net.anything.anythingapp.R

enum class SignKeys(val key: String) {
    CATEGORY_ONE(Signs.FIELD_CATEGORY.keyOf(Signs.SIGN_ONE)),
    CATEGORY_TWO(Signs.FIELD_CATEGORY.keyOf(Signs.SIGN_TWO)),
    CATEGORY_THREE(Signs.FIELD_CATEGORY.keyOf(Signs.SIGN_THREE)),
    NAME_ONE(Signs.FIELD_NAME.keyOf(Signs.SIGN_ONE)),
    NAME_TWO(Signs.FIELD_NAME.keyOf(Signs.SIGN_TWO)),
    NAME_THREE(Signs.FIELD_NAME.keyOf(Signs.SIGN_THREE))
}

enum class Signs(val tag: String, @DrawableRes val res: Int?) {
    SIGN_ONE("1", R.drawable.ic_filter_sign_1),
    SIGN_TWO("2", R.drawable.ic_filter_sign_2),
    SIGN_THREE("3", R.drawable.ic_filter_sign_3),
    FIELD_CATEGORY("Category", null),
    FIELD_NAME("Name", null);

    fun keyOf(sign: Signs): String {
        return "${this.tag} ${sign.tag}"
    }
}

data class SignPreference(
    val field: String,
    val sign: String
)