package uz.example.android.core.sharedpreferences

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class AbstractSetting<T> : StorageSetting<T> {

    lateinit var sharedPreferencesProvider: SharedPreferencesProvider
    protected abstract val cache: Boolean
    protected abstract val key: String

    final override val flow: () -> Flow<T> = { createFlow() }

    override val value: T
        get() = runBlocking(Dispatchers.IO) { read() }

    final override suspend fun updateSync(value: T) {
        persistValue(value)
    }

    override fun updateAsync(value: T) {
        if (cache) {
            sharedPreferencesProvider.prefs.updateCache(key, value)
        }
        GlobalScope.launch {
            updateSync(value)
        }
    }

    protected abstract fun createFlow(): Flow<T>
    protected abstract suspend fun persistValue(value: T)
}
