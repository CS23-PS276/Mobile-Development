package com.cs23_ps276.sahabatlansia.data.notification_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cs23_ps276.sahabatlansia.data.notification_database.model.Task

@Dao
interface OnDataBaseAction {

    @Query("SELECT * FROM Task")
    fun getAllTasksList(): List<Task>

    @Query("DELETE FROM Task")
    fun truncateTheList()

    @Insert
    fun insertDataIntoTaskList(task: Task)

    @Query("DELETE FROM Task WHERE taskId = :taskId")
    fun deleteTaskFromId(taskId: Int)

    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    fun selectDataFromAnId(taskId: Int): Task

    @Query("UPDATE Task SET taskTitle = :taskTitle, taskDescription = :taskDescription, date = :taskDate, " +
            "lastAlarm = :taskTime, event = :taskEvent WHERE taskId = :taskId")
    fun updateAnExistingRow(
        taskId: Int,
        taskTitle: String,
        taskDescription: String,
        taskDate: String,
        taskTime: String,
        taskEvent: String
    )
}