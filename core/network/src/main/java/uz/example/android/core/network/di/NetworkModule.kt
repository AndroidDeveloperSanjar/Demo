package uz.example.android.core.network.di

import org.koin.dsl.module

val NetworkModule = module {
    includes(RetrofitModule)
}
