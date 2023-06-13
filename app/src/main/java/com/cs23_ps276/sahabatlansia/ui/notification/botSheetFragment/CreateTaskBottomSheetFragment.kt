package com.cs23_ps276.sahabatlansia.ui.notification.botSheetFragment


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.cs23_ps276.sahabatlansia.data.notification_database.DatabaseClient
import com.cs23_ps276.sahabatlansia.data.notification_database.model.Task
import com.cs23_ps276.sahabatlansia.databinding.FragmentCreateTaskBinding
import com.cs23_ps276.sahabatlansia.ui.notification.NotificationActivity
import com.cs23_ps276.sahabatlansia.ui.notification.broadcastReceiver.AlarmBroadcastReceiver
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import java.util.GregorianCalendar

class CreateTaskBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    var addTask: Button? = null
    var taskId = 0
    var isEdit = false
    var task: Task? = null
    var mYear = 0
    var mMonth = 0
    var mDay = 0
    var mHour = 0
    var mMinute = 0
    var setRefreshListener: SetRefreshListener? = null
    var alarmManager: AlarmManager? = null
    var timePickerDialog: TimePickerDialog? = null
    var datePickerDialog: DatePickerDialog? = null
    var activity: NotificationActivity? = null
    private val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    fun setTaskId(
        taskId: Int,
        isEdit: Boolean,
        setRefreshListener: SetRefreshListener?,
        activity: NotificationActivity?
    ) {
        this.taskId = taskId
        this.isEdit = isEdit
        this.activity = activity
        this.setRefreshListener = setRefreshListener
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "ClickableViewAccessibility")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        _binding = FragmentCreateTaskBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        binding.addTask.setOnClickListener { view: View? -> if (validateFields()) createTask() }
        if (isEdit) {
            showTaskFromId()
        }
        binding.taskDate.setOnTouchListener { view: View?, motionEvent: MotionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                val c = Calendar.getInstance()
                mYear = c[Calendar.YEAR]
                mMonth = c[Calendar.MONTH]
                mDay = c[Calendar.DAY_OF_MONTH]
                datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                        binding.taskDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        datePickerDialog!!.dismiss()
                    }, mYear, mMonth, mDay
                )
                datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 1000
                datePickerDialog!!.show()
            }
            true
        }
        binding.taskTime.setOnTouchListener { view: View?, motionEvent: MotionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                // Get Current Time
                val c = Calendar.getInstance()
                mHour = c[Calendar.HOUR_OF_DAY]
                mMinute = c[Calendar.MINUTE]

                // Launch Time Picker Dialog
                timePickerDialog = TimePickerDialog(
                    requireActivity(),
                    { view12: TimePicker?, hourOfDay: Int, minute: Int ->
                        binding.taskTime.setText("$hourOfDay:$minute")
                        timePickerDialog!!.dismiss()
                    }, mHour, mMinute, false
                )
                timePickerDialog!!.show()
            }
            true
        }
    }

    fun validateFields(): Boolean {
        return if (binding.addTaskTitle.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter a valid title", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.addTaskDescription.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter a valid description", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.taskDate.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter date", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.taskTime.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter time", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.taskEvent.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(activity, "Please enter an event", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createTask() {
        class SaveTaskInBackend : AsyncTask<Void?, Void?, Void?>() {
            @SuppressLint("WrongThread")
            override fun doInBackground(vararg voids: Void?): Void? {
                val createTask = Task()
                createTask.taskTitle = binding.addTaskTitle.text.toString()
                createTask.taskDescrption = binding.addTaskDescription.text.toString()
                createTask.date = binding.taskDate.text.toString()
                createTask.lastAlarm = binding.taskTime.text.toString()
                createTask.event = binding.taskEvent.text.toString()
                val appDatabase = DatabaseClient.getInstance(context!!)?.appDatabase
                val context = appDatabase?.dataBaseAction()
                if (!isEdit) {
                    context?.insertDataIntoTaskList(createTask)
                } else {
                    context?.updateAnExistingRow(
                        taskId,
                        binding.addTaskTitle.text.toString(),
                        binding.addTaskDescription.text.toString(),
                        binding.taskDate.text.toString(),
                        binding.taskTime.text.toString(),
                        binding.taskEvent.text.toString()
                    )
                }
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    createAnAlarm()
                }
                setRefreshListener!!.refresh()
                Toast.makeText(activity, "Your event has been added", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }

        val st = SaveTaskInBackend()
        st.execute()
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun createAnAlarm() {
        try {
            val items1 =
                binding.taskDate.text.toString().split("-".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val dd = items1[0]
            val month = items1[1]
            val year = items1[2]
            val itemTime =
                binding.taskTime.text.toString().split(":".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val hour = itemTime[0]
            val min = itemTime[1]
            val cur_cal: Calendar = GregorianCalendar()
            cur_cal.timeInMillis = System.currentTimeMillis()
            val cal: Calendar = GregorianCalendar()
            cal[Calendar.HOUR_OF_DAY] = hour.toInt()
            cal[Calendar.MINUTE] = min.toInt()
            cal[Calendar.SECOND] = 0
            cal[Calendar.MILLISECOND] = 0
            cal[Calendar.DATE] = dd.toInt()
            val alarmIntent = Intent(activity, AlarmBroadcastReceiver::class.java)
            alarmIntent.putExtra("TITLE", binding.addTaskTitle.text.toString())
            alarmIntent.putExtra("DESC", binding.addTaskDescription.text.toString())
            alarmIntent.putExtra("DATE", binding.taskDate.text.toString())
            alarmIntent.putExtra("TIME", binding.taskTime.text.toString())
            val pendingIntent = PendingIntent.getBroadcast(
                activity,
                count,
                alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager!!.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    cal.timeInMillis,
                    pendingIntent
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmManager!!.setExact(
                        AlarmManager.RTC_WAKEUP,
                        cal.timeInMillis,
                        pendingIntent
                    )
                } else {
                    alarmManager!![AlarmManager.RTC_WAKEUP, cal.timeInMillis] = pendingIntent
                }
                count++
                val intent = PendingIntent.getBroadcast(
                    activity,
                    count,
                    alarmIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager!!.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        cal.timeInMillis - 600000,
                        intent
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        alarmManager!!.setExact(
                            AlarmManager.RTC_WAKEUP,
                            cal.timeInMillis - 600000,
                            intent
                        )
                    } else {
                        alarmManager!![AlarmManager.RTC_WAKEUP, cal.timeInMillis - 600000] = intent
                    }
                }
                count++
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showTaskFromId() {
        class ShowTaskFromId : AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            override fun doInBackground(vararg voids: Void): Void? {
                task = activity?.let {
                    DatabaseClient.getInstance(it.applicationContext)?.appDatabase
                        ?.dataBaseAction()?.selectDataFromAnId(taskId)
                }
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                setDataInUI()
            }
        }

        val st = ShowTaskFromId()
        st.execute()
    }

    private fun setDataInUI() {
        binding.addTaskTitle.setText(task?.taskTitle)
        binding.addTaskDescription.setText(task?.taskDescrption)
        binding.taskDate.setText(task?.date)
        binding.taskTime.setText(task?.lastAlarm)
        binding.taskEvent.setText(task?.event)
    }

    interface SetRefreshListener {
        fun refresh()
    }

    companion object {
        var count = 0
    }
}