package com.cs23_ps276.sahabatlansia.data.notification_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cs23_ps276.sahabatlansia.data.notification_database.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataBaseAction(): OnDataBaseAction

    companion object {
        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return appDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Task.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                appDatabase = instance
                instance
            }
        }
    }
}