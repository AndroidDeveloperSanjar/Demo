package uz.example.demo.presentation.settings

import androidx.lifecycle.ViewModel
import uz.example.android.core.sharedpreferences.storages.ResourceStorage

class SettingsViewModel(
    private val resourceStorage: ResourceStorage
) : ViewModel() {

    fun changeLanguage(lang: String) {
        resourceStorage.language.updateAsync(lang)
    }

    fun changeTheme(theme: String) {
        resourceStorage.theme.updateAsync(theme)
    }

}