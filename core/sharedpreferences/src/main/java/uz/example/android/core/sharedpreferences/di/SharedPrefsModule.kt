package uz.example.android.core.sharedpreferences.di

import uz.example.android.core.sharedpreferences.PreferencesDataSource
import uz.example.android.core.sharedpreferences.SecuredPreferencesDataSource
import uz.example.android.core.sharedpreferences.SharedPreferencesProvider
import uz.example.android.core.sharedpreferences.storages.ResourceStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import uz.example.android.core.sharedpreferences.storages.UserStorage

private const val SHARED_PREFERENCES_NAME = "uz.example.demo"

val SharedPrefsModule = module {
    single<PreferencesDataSource> { SecuredPreferencesDataSource(get(), SHARED_PREFERENCES_NAME) }
    singleOf(::ResourceStorage) bind SharedPreferencesProvider::class
    singleOf(::UserStorage) bind SharedPreferencesProvider::class
}
