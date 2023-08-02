package uz.example.android.core.sharedpreferences.storages

import uz.example.android.core.sharedpreferences.PrefKeys
import uz.example.android.core.sharedpreferences.PreferencesDataSource
import uz.example.android.core.sharedpreferences.SharedPreferencesProvider
import uz.example.android.core.sharedpreferences.extension.sharedPref

class ResourceStorage(override val prefs: PreferencesDataSource) : SharedPreferencesProvider {
    val language by sharedPref(PrefKeys.Resource.LANGUAGE, "en")
    val theme by sharedPref(PrefKeys.Resource.THEME, "light")
}