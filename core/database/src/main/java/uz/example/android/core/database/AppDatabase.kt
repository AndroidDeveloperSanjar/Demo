package uz.example.android.core.database

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "database_name"
    }
}
