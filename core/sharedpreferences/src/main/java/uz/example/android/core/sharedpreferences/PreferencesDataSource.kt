package uz.example.android.core.sharedpreferences

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

    fun containsBoolean(key: String): Flow<Boolean>

    fun containsFloat(key: String): Flow<Boolean>

    fun containsInt(key: String): Flow<Boolean>

    fun containsLong(key: String): Flow<Boolean>

    fun containsString(key: String): Flow<Boolean>

    fun getBoolean(key: String, defaultValue: Boolean, cache: Boolean = true): Flow<Boolean>

    fun getFloat(key: String, defaultValue: Float, cache: Boolean = true): Flow<Float>

    fun getInt(key: String, defaultValue: Int, cache: Boolean = true): Flow<Int>

    fun getLong(key: String, defaultValue: Long, cache: Boolean = true): Flow<Long>

    fun getString(key: String, defaultValue: String, cache: Boolean = true): Flow<String>

    fun <E : Enum<E>> getEnum(key: String, default: E, cache: Boolean = true): Flow<E>

    suspend fun removeAll()

    suspend fun saveBoolean(key: String, value: Boolean, cache: Boolean = true)

    suspend fun saveFloat(key: String, value: Float, cache: Boolean = true)

    suspend fun saveInt(key: String, value: Int, cache: Boolean = true)

    suspend fun saveLong(key: String, value: Long, cache: Boolean = true)

    suspend fun saveString(key: String, value: String, cache: Boolean = true)

    suspend fun <E : Enum<E>> saveEnum(key: String, value: E, cache: Boolean = true)

    fun <T> updateCache(key: String, value: T)
}
