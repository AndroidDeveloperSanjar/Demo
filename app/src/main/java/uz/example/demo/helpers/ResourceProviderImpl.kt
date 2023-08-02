package uz.example.demo.helpers

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uz.example.android.core.resourceprovider.ResourceProvider
import uz.example.android.util.extension.color
import uz.example.android.util.extension.drawable

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {
    override fun getColor(@ColorRes id: Int) = context.color(id)

    override fun getString(@StringRes id: Int) = context.getString(id)

    override fun getString(@StringRes id: Int, vararg args: Any) = context.getString(id, args)

    override fun getDrawable(@DrawableRes id: Int) = context.drawable(id)
}