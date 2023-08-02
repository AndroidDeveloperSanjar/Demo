package uz.example.demo.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.example.android.data.common.BaseResponseWrapper

open class BaseViewModel : ViewModel() {

    private val _errorEntityState = MutableStateFlow<Pair<ErrorEntity?, String?>?>(null)
    val errorEntityState = _errorEntityState.asStateFlow()

    private val _loaderState = MutableStateFlow<Pair<Boolean, String?>?>(null)
    val loaderState = _loaderState.asStateFlow()

    protected fun setErrorEntity(entity: ErrorEntity?, type: String? = null) {
        _errorEntityState.value = entity to type
    }

    protected fun setLoader(isLoading: Boolean, apiName: String? = null) {
        _loaderState.value = isLoading to apiName
    }

    protected suspend inline fun <T> withErrorHandling(
        errorType: String? = null,
        showLoader: Boolean = false,
        crossinline request: suspend () -> BaseResponseWrapper<T>
    ): T? {
        return withResponseCodeErrorHandling(errorType, showLoader, request)?.first
    }

    protected suspend inline fun <T> withResponseCodeErrorHandling(
        errorType: String? = null,
        showLoader: Boolean = false,
        crossinline request: suspend () -> BaseResponseWrapper<T>
    ): Pair<T, Int>? {
        runCatching {
            if (showLoader) setLoader(true, errorType)
            val response = request.invoke()
            val body = response.body()
            val code = response.code()
            if (showLoader) setLoader(false, errorType)

            when {
                !response.isSuccessful -> {
                    setErrorEntity(response.code().errorEntity(response.errorBody()), errorType)
                }

                body == null -> {
                    setErrorEntity(ErrorEntity.ApiErrorEntity.JSONException, errorType)
                }

                else -> {
                    return body.data to code
                }
            }
        }.exceptionOrNull()?.let {
            if (showLoader) setLoader(false, errorType)
            setErrorEntity(it.errorEntity(), errorType)
        }

        return null
    }
}