package uz.example.demo.di.core

import uz.example.android.core.sharedpreferences.di.SharedPrefsModule
import org.koin.dsl.module
import uz.example.android.core.dispatchers.DispatchersModule
import uz.example.android.core.network.di.NetworkModule

val AppCoreModule = module {
    includes(DispatchersModule, NetworkModule, SharedPrefsModule)
}
