package uz.example.demo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import uz.example.demo.di.app.AppModule
import uz.example.demo.di.app.PresentationModule
import uz.example.demo.di.core.AppCoreModule

fun Application.initKoin() {
    GlobalContext.startKoin {
        androidLogger()
        androidContext(this@initKoin)
        modules(
            AppModule,
            AppCoreModule,
            PresentationModule
        )
    }
}
