package uz.example.android.core.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

class SecuredPreferencesDataSource constructor(
    context: Context, fileName: String, private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PreferencesDataSource {

    private val encryptedSecuredPreferences by lazy {
        try {
            val masterKeyAlias =
                MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
            EncryptedSharedPreferences.create(
                context,
                fileName,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (ignore: Exception) {
            context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        }
    }
    private val cacheMap: MutableMap<String, Any> = HashMap()
    private val mutexes = ConcurrentHashMap<String, Mutex>()

    override fun containsBoolean(key: String): Flow<Boolean> =
        flowOf(encryptedSecuredPreferences.contains(key)).flowOn(dispatcher)

    override fun containsFloat(key: String): Flow<Boolean> =
        flowOf(encryptedSecuredPreferences.contains(key)).flowOn(dispatcher)

    override fun containsInt(key: String): Flow<Boolean> =
        flowOf(encryptedSecuredPreferences.contains(key)).flowOn(dispatcher)

    override fun containsLong(key: String): Flow<Boolean> =
        flowOf(encryptedSecuredPreferences.contains(key)).flowOn(dispatcher)

    override fun containsString(key: String): Flow<Boolean> =
        flowOf(encryptedSecuredPreferences.contains(key)).flowOn(dispatcher)

    override fun getBoolean(key: String, defaultValue: Boolean, cache: Boolean): Flow<Boolean> =
        createFlow(cache, key) { encryptedSecuredPreferences.getBoolean(key, defaultValue) }

    override fun getFloat(key: String, defaultValue: Float, cache: Boolean): Flow<Float> =
        createFlow(cache, key) { encryptedSecuredPreferences.getFloat(key, defaultValue) }

    override fun getInt(key: String, defaultValue: Int, cache: Boolean): Flow<Int> =
        createFlow(cache, key) { encryptedSecuredPreferences.getInt(key, defaultValue) }

    override fun getLong(key: String, defaultValue: Long, cache: Boolean): Flow<Long> =
        createFlow(cache, key) { encryptedSecuredPreferences.getLong(key, defaultValue) }

    override fun getString(key: String, defaultValue: String, cache: Boolean): Flow<String> =
        createFlow(cache, key) {
            encryptedSecuredPreferences.getString(key, defaultValue) ?: defaultValue
        }

    override fun <E : Enum<E>> getEnum(key: String, default: E, cache: Boolean): Flow<E> =
        createFlowEnum(cache, key, default)

    override suspend fun removeAll() {
        cacheMap.clear()
        withContext(dispatcher) {
            encryptedSecuredPreferences.edit(commit = true) {
                clear()
            }
        }
    }

    override suspend fun saveBoolean(key: String, value: Boolean, cache: Boolean) {
        save(key, value, cache) {
            putBoolean(key, value)
        }
    }

    override suspend fun saveFloat(key: String, value: Float, cache: Boolean) {
        save(key, value, cache) {
            putFloat(key, value)
        }
    }

    override suspend fun saveInt(key: String, value: Int, cache: Boolean) {
        save(key, value, cache) {
            putInt(key, value)
        }
    }

    override suspend fun saveLong(key: String, value: Long, cache: Boolean) {
        save(key, value, cache) {
            putLong(key, value)
        }
    }

    override suspend fun saveString(key: String, value: String, cache: Boolean) {
        save(key, value, cache) {
            putString(key, value)
        }
    }

    override suspend fun <E : Enum<E>> saveEnum(key: String, value: E, cache: Boolean) {
        save(key, value, cache) {
            putString(key, value.name)
        }
    }

    override fun <T> updateCache(key: String, value: T) {
        cacheMap[key] = value!!
    }

    private suspend fun <T> save(
        key: String, value: T, cache: Boolean, action: SharedPreferences.Editor.() -> Unit
    ) {
        if (cache) {
            updateCache(key, value!!)
        }
        val mutex = mutexes.computeIfAbsent(key) { Mutex() }
        mutex.withLock {
            withContext(dispatcher) {
                encryptedSecuredPreferences.edit(commit = true) {
                    action(this)
                }
            }
        }
    }

    private inline fun <reified T> createFlow(
        cache: Boolean, key: String, crossinline readValue: () -> T
    ): Flow<T> {
        return flow {
            if (cache && cacheMap[key] != null) {
                emit(cacheMap[key] as T)
            } else {
                val mutex = mutexes.computeIfAbsent(key) { Mutex() }
                mutex.withLock {
                    val value = readValue.invoke()
                    if (cache) {
                        updateCache(key, value!!)
                    }
                    emit(value)
                }
            }
        }.flowOn(dispatcher)
    }

    private fun <E : Enum<E>> createFlowEnum(
        cache: Boolean, key: String, default: E
    ): Flow<E> {
        return flow {
            if (cache && cacheMap[key] != null) {
                emit(cacheMap[key] as E)
            } else {
                val mutex = mutexes.computeIfAbsent(key) { Mutex() }
                mutex.withLock {
                    val find = default::class.java.enumConstants?.find {
                        it.name == encryptedSecuredPreferences.getString(key, default.name)
                    }
                    val value = find ?: default
                    if (cache) {
                        updateCache(key, value)
                    }
                    emit(value)
                }
            }
        }.flowOn(dispatcher)
    }
}
