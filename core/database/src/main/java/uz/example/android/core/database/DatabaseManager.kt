package uz.example.android.core.database

import kotlinx.coroutines.withContext
import uz.example.android.core.dispatchers.DispatchersProvider

class DatabaseManager(
    private val appDatabase: AppDatabase,
    private val dispatchers: DispatchersProvider
) {
    suspend fun clear() = withContext(dispatchers.io) {
        appDatabase.clearAllTables()
    }
}
