package uz.example.demo.base
import android.system.ErrnoException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

sealed class ErrorEntity {
    sealed class ApiErrorEntity : ErrorEntity() {
        object JsonParseException : ApiErrorEntity()
        object NullPointerException : ApiErrorEntity()
        object JsonSyntaxException : ApiErrorEntity()
        object JSONException : ApiErrorEntity()
        data class HttpException(
            val code: Int? = null,
            val errorMessage: String? = null
        ) : ApiErrorEntity()

        object MalformedJsonException : ApiErrorEntity()
        object UnknownHostException : ApiErrorEntity()
        object IOException : ApiErrorEntity()
        object SSLHandshakeException : ApiErrorEntity()
        object SSLException : ApiErrorEntity()
        object SocketException : ApiErrorEntity()
        object SocketTimeoutException : ApiErrorEntity()
        object KotlinNullPointerException : ApiErrorEntity()
        data class OtherApiException(
            val errorMessage: String? = null
        ) : ApiErrorEntity()
    }

    sealed class ApiCodeErrorEntity : ErrorEntity() {
        data class Error300(val errorBody: String? = null) : ApiCodeErrorEntity()
        object Error401 : ApiCodeErrorEntity()
        object Error407 : ApiCodeErrorEntity()
        object Error409 : ApiCodeErrorEntity()
        data class Error423(val errorBody: String? = null) : ApiCodeErrorEntity()
        data class Error426(val errorBody: String? = null) : ApiCodeErrorEntity()
        data class Error503(val errorBody: String? = null) : ApiCodeErrorEntity()
        data class Error504M418(val errorBody: String? = null) : ApiCodeErrorEntity()
        data class ErrorOtherCode(val errorBody: String? = null) : ApiCodeErrorEntity()
    }
}

fun Throwable.errorEntity(): ErrorEntity {
    return when (this) {
        is JsonSyntaxException -> ErrorEntity.ApiErrorEntity.JsonSyntaxException
        is JsonParseException -> ErrorEntity.ApiErrorEntity.JsonParseException
        is JSONException -> ErrorEntity.ApiErrorEntity.JSONException
        is KotlinNullPointerException -> ErrorEntity.ApiErrorEntity.KotlinNullPointerException
        is NullPointerException -> ErrorEntity.ApiErrorEntity.NullPointerException
        is HttpException -> ErrorEntity.ApiErrorEntity.HttpException(
            this.code(),
            message ?: localizedMessage
        )
        is com.google.gson.stream.MalformedJsonException -> ErrorEntity.ApiErrorEntity.MalformedJsonException
        is UnknownHostException -> ErrorEntity.ApiErrorEntity.UnknownHostException
        is SSLHandshakeException -> ErrorEntity.ApiErrorEntity.SSLHandshakeException
        is SSLException -> ErrorEntity.ApiErrorEntity.SSLException
        is SocketException -> ErrorEntity.ApiErrorEntity.SocketException
        is SocketTimeoutException -> ErrorEntity.ApiErrorEntity.SocketTimeoutException
        is IOException -> ErrorEntity.ApiErrorEntity.IOException
        else -> ErrorEntity.ApiErrorEntity.OtherApiException(
            cause?.localizedMessage ?: cause?.message ?: "Nothing"
        )
    }
}

fun Int.errorEntity(errorBody: ResponseBody?): ErrorEntity {
    return when (this) {
        300 -> ErrorEntity.ApiCodeErrorEntity.Error300(errorBody?.handle())
        401 -> ErrorEntity.ApiCodeErrorEntity.Error401
        407 -> ErrorEntity.ApiCodeErrorEntity.Error407
        409 -> ErrorEntity.ApiCodeErrorEntity.Error409
        423 -> ErrorEntity.ApiCodeErrorEntity.Error423(errorBody?.handle())
        426 -> ErrorEntity.ApiCodeErrorEntity.Error426(errorBody?.handle())
        503 -> ErrorEntity.ApiCodeErrorEntity.Error503(errorBody?.handle())
        504, 418 -> ErrorEntity.ApiCodeErrorEntity.Error504M418(errorBody?.handle())
        else -> ErrorEntity.ApiCodeErrorEntity.ErrorOtherCode(errorBody?.handle())
    }
}

fun ResponseBody?.handle(): String? {
    val byteArray: ByteArray = this?.bytes() ?: return null
    val jsonObject = JSONObject(String(byteArray))
    return try {
        jsonObject.getString("errorMessage")
    } catch (e: JSONException) {
        return try {
            jsonObject.getString("error")
        } catch (e: Exception) {
            "not found error field"
        }
    }
}

fun Throwable.isConnectionThrowable(): Boolean =
    this is ConnectException ||
            this is UnknownHostException ||
            this is SocketTimeoutException ||
            this is TimeoutException ||
            this is InterruptedIOException ||
            this is ErrnoException ||
            this is SocketException ||
            this is SSLHandshakeException ||
            this is EOFException ||
            cause?.isConnectionThrowable() == true
