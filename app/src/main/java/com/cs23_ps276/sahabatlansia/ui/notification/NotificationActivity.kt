package com.cs23_ps276.sahabatlansia.ui.notification

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.data.notification_database.DatabaseClient
import com.cs23_ps276.sahabatlansia.data.notification_database.model.Task
import com.cs23_ps276.sahabatlansia.databinding.ActivityNotificationBinding
import com.cs23_ps276.sahabatlansia.ui.notification.adapter.TaskAdapter
import com.cs23_ps276.sahabatlansia.ui.notification.botSheetFragment.CreateTaskBottomSheetFragment
import com.cs23_ps276.sahabatlansia.ui.notification.botSheetFragment.ShowCalendarViewBottomSheet
import com.cs23_ps276.sahabatlansia.ui.notification.broadcastReceiver.AlarmBroadcastReceiver

class NotificationActivity : AppCompatActivity(), CreateTaskBottomSheetFragment.SetRefreshListener {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var taskAdapter: TaskAdapter
    private var tasks: List<Task> = ArrayList()
    private var activity: NotificationActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpAdapter()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val receiver = ComponentName(this, AlarmBroadcastReceiver::class.java)
        val pm = packageManager
        pm.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
        Glide.with(applicationContext).load(R.drawable.first_note).into(binding.noDataImage)

        binding.addTask.setOnClickListener { view: View? ->
            val createTaskBottomSheetFragment = CreateTaskBottomSheetFragment()
            createTaskBottomSheetFragment.setTaskId(0, false, this, this@NotificationActivity)
            createTaskBottomSheetFragment.show(
                supportFragmentManager,
                createTaskBottomSheetFragment.tag
            )
        }

        getSavedTasks()

        binding.calendar.setOnClickListener { view: View? ->
            val showCalendarViewBottomSheet = ShowCalendarViewBottomSheet()
            showCalendarViewBottomSheet.show(
                supportFragmentManager,
                showCalendarViewBottomSheet.tag
            )
        }
    }

    private fun setUpAdapter() {
        taskAdapter = TaskAdapter(this, tasks.toMutableList(), this)
        binding.taskRecycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.taskRecycler.adapter = taskAdapter
    }

    private fun getSavedTasks() {
        class GetSavedTasks : AsyncTask<Void?, Void?, List<Task>?>() {
            override fun doInBackground(vararg voids: Void?): List<Task>? {
                try {
                    val databaseClient = DatabaseClient.getInstance(applicationContext)?.appDatabase
                    return databaseClient?.dataBaseAction()?.getAllTasksList()
                } catch (e: Exception) {
                    e.printStackTrace()

                    return null
                }
            }

            override fun onPostExecute(tasks: List<Task>?) {
                super.onPostExecute(tasks)
                if (tasks != null) {
                    binding.noDataImage.visibility = if (tasks.isEmpty()) View.VISIBLE else View.GONE
                    this@NotificationActivity.tasks = tasks
                    setUpAdapter()
                } else {

                }
            }
        }

        val savedTasks = GetSavedTasks()
        savedTasks.execute()
    }

    override fun refresh() {
        getSavedTasks()
    }
}