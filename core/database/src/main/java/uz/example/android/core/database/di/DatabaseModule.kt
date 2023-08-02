package uz.example.android.core.database.di

import androidx.room.Room
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.dsl.module
import uz.example.android.core.database.AppDatabase
import uz.example.android.core.database.DatabaseManager

val DatabaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { DatabaseManager(get(), get()) }

    single {
        SupportFactory(SQLiteDatabase.getBytes("u[/Df~B8:2xSg&&t".toCharArray()))
    }

    //TODO singleton DAO -> single { get<AppDatabase>().testDao() }
}
