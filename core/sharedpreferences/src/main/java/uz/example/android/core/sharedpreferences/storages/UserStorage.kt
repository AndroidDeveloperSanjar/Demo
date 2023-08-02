package uz.example.android.core.sharedpreferences.storages

import uz.example.android.core.sharedpreferences.PrefKeys
import uz.example.android.core.sharedpreferences.PreferencesDataSource
import uz.example.android.core.sharedpreferences.SharedPreferencesProvider
import uz.example.android.core.sharedpreferences.extension.sharedPref

class UserStorage(override val prefs: PreferencesDataSource) : SharedPreferencesProvider {

    val isLogin by sharedPref(PrefKeys.UserInfo.IS_LOGIN, false)

}