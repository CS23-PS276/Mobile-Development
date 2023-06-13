package com.cs23_ps276.sahabatlansia.data.notification_database

import android.content.Context


class DatabaseClient private constructor(private val mCtx: Context) {

    val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstance(mCtx)
    }

    companion object {
        @Volatile
        private var mInstance: DatabaseClient? = null

        fun getInstance(mCtx: Context): DatabaseClient {
            return mInstance ?: synchronized(this) {
                val instance = DatabaseClient(mCtx)
                mInstance = instance
                instance
            }
        }
    }
}