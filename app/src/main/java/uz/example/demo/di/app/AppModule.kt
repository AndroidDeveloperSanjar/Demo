package uz.example.demo.di.app

import org.koin.dsl.module
import uz.example.android.core.resourceprovider.ResourceProvider
import uz.example.demo.helpers.ResourceProviderImpl

val AppModule = module {
    single<ResourceProvider> { ResourceProviderImpl(get()) }
}
