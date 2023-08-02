package uz.example.demo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.yariksoffice.lingver.Lingver
import org.koin.android.ext.android.inject
import uz.example.android.core.sharedpreferences.storages.ResourceStorage
import uz.example.demo.di.initKoin
import uz.example.demo.utils.StringResource

class App : Application() {

    private val resourceStorage by inject<ResourceStorage>()

    override fun onCreate() {
        super.onCreate()
        initKoin()
        setupTheme()
        Lingver.init(this, resourceStorage.language.value)
    }

    internal fun setupTheme() {
        when (resourceStorage.theme.value) {
            getString(StringResource.light) -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }

            getString(StringResource.night) -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            }
        }
    }
}