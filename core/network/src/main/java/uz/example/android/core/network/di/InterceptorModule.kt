package uz.example.android.core.network.di

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.mocklets.pluto.PlutoInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

internal val InterceptorModule = module {
    factory { PlutoInterceptor() }
    factory { OkHttpProfilerInterceptor() }
    factory { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
}
