package net.anything.utils.uiBuilder.preference

import androidx.annotation.DrawableRes
import net.anything.anythingapp.R

enum class Signs(val tag: String, @DrawableRes val res: Int?) {
    SIGN_ONE("1", R.drawable.ic_filter_sign_1),
    SIGN_TWO("2", R.drawable.ic_filter_sign_2),
    SIGN_THREE("3", R.drawable.ic_filter_sign_3),
    FIELD_CATEGORY("category", null),
    FIELD_NAME("name", null)
}