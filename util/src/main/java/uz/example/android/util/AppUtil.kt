package uz.example.android.util

import android.content.Context
import uz.example.android.util.extension.getPackageInfoCompat

object AppUtil {
    fun getVersionName(context: Context) = try {
        context.packageManager.getPackageInfoCompat(context.packageName, 0).versionName.toString()
    } catch (e: Exception) {
        "v0.0.0"
    }

    @Suppress("Deprecated")
    fun getVersionCode(context: Context) = try {
        context.packageManager.getPackageInfoCompat(context.packageName, 0).versionCode.toString()
    } catch (e: Exception) {
        0
    }
}