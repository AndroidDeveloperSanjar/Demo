package uz.example.android.data.common

import androidx.annotation.Keep
import retrofit2.Response

typealias BaseResponseWrapper<T> = Response<BaseResponse<T>>

@Keep
data class BaseResponse<T>(
    val data: T, val errorMessage: String, val timestamp: Long
)