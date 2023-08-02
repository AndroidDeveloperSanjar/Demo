package uz.example.android.core.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(vararg obj: T): Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(list: List<T>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(obj: T): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCache(vararg obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCache(list: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCache(obj: T)

    @Delete
    suspend fun deleteCache(vararg obj: T)

    @Delete
    suspend fun deleteCache(list: List<T>)

    @Delete
    suspend fun deleteCache(obj: T)
}
