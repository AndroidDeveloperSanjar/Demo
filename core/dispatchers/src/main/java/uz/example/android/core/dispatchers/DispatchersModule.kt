package uz.example.android.core.dispatchers

import org.koin.dsl.module

val DispatchersModule = module {
    single { DispatchersProvider() }
}
