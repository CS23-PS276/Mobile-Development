package com.cs23_ps276.sahabatlansia.notification

import android.app.Application
import com.cs23_ps276.sahabatlansia.notification.data.TaskDatabase

class BaseApplication : Application() {
    val database: TaskDatabase by lazy { TaskDatabase.getDatabase(this) }
}
