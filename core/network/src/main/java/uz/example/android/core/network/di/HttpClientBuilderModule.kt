package uz.example.android.core.network.di

import com.example.android.core.network.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import uz.example.android.core.network.config.OkHttpClientConfig
import uz.example.android.core.network.config.configure
import java.util.concurrent.TimeUnit

const val OKHTTP_TIMEOUT = 60L

val HttpClientBuilderModule = module {
    includes(InterceptorModule)

    factory { (config: OkHttpClientConfig) ->
        OkHttpClient().newBuilder().connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS).configure(config).apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(get<OkHttpProfilerInterceptor>())
                    addInterceptor(get<HttpLoggingInterceptor>())
                }
            }
    }
}
