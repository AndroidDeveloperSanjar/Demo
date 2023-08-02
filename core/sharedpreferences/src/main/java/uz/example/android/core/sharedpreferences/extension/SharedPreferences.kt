package uz.example.android.core.sharedpreferences.extension

import uz.example.android.core.sharedpreferences.AbstractSetting
import uz.example.android.core.sharedpreferences.PreferencesDataSource
import uz.example.android.core.sharedpreferences.SharedPreferencesProvider
import uz.example.android.core.sharedpreferences.StorageSetting
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KProperty

inline fun <reified T> sharedPref(key: String, defaultValue: T = default(), cache: Boolean = true) =
    object : AbstractSetting<T>() {
        override val cache: Boolean
            get() = cache
        override val key: String
            get() = key

        override fun createFlow(): Flow<T> {
            return sharedPreferencesProvider.prefs[key, defaultValue, cache]
        }

        override suspend fun persistValue(value: T) {
            sharedPreferencesProvider.prefs.setValue(key, value, cache)
        }

        override fun getValue(
            thisRef: SharedPreferencesProvider, property: KProperty<*>
        ): StorageSetting<T> {
            sharedPreferencesProvider = thisRef
            return this
        }
    }

inline fun <reified T : Enum<T>> enumPref(key: String, defaultValue: T, cache: Boolean = true) =
    object : AbstractSetting<T>() {
        override val cache: Boolean
            get() = cache
        override val key: String
            get() = key

        override fun createFlow(): Flow<T> {
            return sharedPreferencesProvider.prefs.getEnum(key, defaultValue, cache)
        }

        override suspend fun persistValue(value: T) {
            sharedPreferencesProvider.prefs.saveEnum(key, value)
        }

        override fun getValue(
            thisRef: SharedPreferencesProvider, property: KProperty<*>
        ): StorageSetting<T> {
            sharedPreferencesProvider = thisRef
            return this
        }
    }

suspend fun <T> PreferencesDataSource.setValue(key: String, value: T, cache: Boolean = true) =
    when (value) {
        is String -> saveString(key, value, cache)
        is Int -> saveInt(key, value, cache)
        is Boolean -> saveBoolean(key, value, cache)
        is Float -> saveFloat(key, value, cache)
        is Long -> saveLong(key, value, cache)
        else -> throw UnsupportedOperationException("Type $value is not supported")
    }

inline operator fun <reified T> PreferencesDataSource.get(
    key: String, defaultValue: T = default(), cache: Boolean = true
): Flow<T> = when (defaultValue) {
    is String -> getString(key, defaultValue, cache) as Flow<T>
    is Int -> getInt(key, defaultValue, cache) as Flow<T>
    is Boolean -> getBoolean(key, defaultValue, cache) as Flow<T>
    is Float -> getFloat(key, defaultValue, cache) as Flow<T>
    is Long -> getLong(key, defaultValue, cache) as Flow<T>
    else -> throw UnsupportedOperationException("Type ${T::class} is not supported")
}

inline fun <reified T> default(): T = when (T::class) {
    String::class -> "" as T
    Int::class -> 0 as T
    Boolean::class -> false as T
    Float::class -> 0F as T
    Long::class -> 0L as T
    else -> throw IllegalArgumentException("Default value not found for type ${T::class}")
}
