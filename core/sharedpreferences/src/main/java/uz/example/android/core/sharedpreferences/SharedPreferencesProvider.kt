package uz.example.android.core.sharedpreferences

interface SharedPreferencesProvider {

    val prefs: PreferencesDataSource

    fun onLogout() {}
}
