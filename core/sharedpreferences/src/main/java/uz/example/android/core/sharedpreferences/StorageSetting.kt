package uz.example.android.core.sharedpreferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlin.properties.ReadOnlyProperty

interface StorageSetting<T> : ReadOnlyProperty<SharedPreferencesProvider, StorageSetting<T>> {

    val flow: () -> Flow<T>

    /*
     * returns a cached value or retrieves the value in a blocking way
     */
    val value: T

    suspend fun updateSync(value: T)
    fun updateAsync(value: T)
    suspend fun read(): T = flow.invoke().first()
}
