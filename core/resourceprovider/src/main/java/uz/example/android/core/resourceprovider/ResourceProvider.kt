package uz.example.android.core.resourceprovider

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getColor(@ColorRes id: Int): Int
    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any): String
    fun getDrawable(@DrawableRes id: Int): Drawable?
}