package uz.example.android.core.network.di

import okhttp3.OkHttpClient
import okhttp3.Protocol
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import uz.example.android.core.network.config.OkHttpClientConfig
import java.util.concurrent.TimeUnit

internal val HttpClientModule = module {
    includes(HttpClientBuilderModule)

    single {
        val config = OkHttpClientConfig {
            retryOnConnectionFailure(false)
            writeTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
            protocols(listOf(Protocol.HTTP_1_1))
        }

        get<OkHttpClient.Builder>(parameters = { parametersOf(config) }).build()
    }
}
