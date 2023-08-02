package uz.example.android.core.network.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.example.android.core.network.ApiUrl

internal val RetrofitModule = module {
    includes(HttpClientModule)

    single {
        Retrofit.Builder().baseUrl(ApiUrl.baseUrl).client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
