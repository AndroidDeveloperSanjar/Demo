package uz.example.android.core.network.config

import okhttp3.OkHttpClient

class OkHttpClientConfig(val action: OkHttpClient.Builder.() -> OkHttpClient.Builder)

fun OkHttpClient.Builder.configure(config: OkHttpClientConfig) = config.action(this)
