package com.cs23_ps276.sahabatlansia.ui.notification.botSheetFragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import com.applandeo.materialcalendarview.EventDay
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.data.notification_database.DatabaseClient
import com.cs23_ps276.sahabatlansia.data.notification_database.model.Task
import com.cs23_ps276.sahabatlansia.databinding.FragmentCalendarViewBinding
import com.cs23_ps276.sahabatlansia.ui.notification.NotificationActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class ShowCalendarViewBottomSheet : BottomSheetDialogFragment() {
    private var binding: FragmentCalendarViewBinding? = null
    private var activity: NotificationActivity? = null
    private var tasks: List<Task> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as NotificationActivity
    }

    private val mBottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi", "ClickableViewAccessibility")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        binding = FragmentCalendarViewBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding!!.root)

        binding?.calendarView?.setHeaderColor(R.color.teal_700)

        savedTasks()

        binding?.back?.setOnClickListener { view: View? -> dialog.dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun savedTasks() {
        class GetSavedTasks : AsyncTask<Void, Void, List<Task>>() {
            override fun doInBackground(vararg voids: Void): List<Task> {
                tasks = DatabaseClient
                    .getInstance(activity!!.applicationContext)
                    ?.appDatabase
                    ?.dataBaseAction()
                    ?.getAllTasksList() ?: tasks
                return tasks
            }

            override fun onPostExecute(tasks: List<Task>) {
                super.onPostExecute(tasks)
                binding?.calendarView?.setEvents(highlightedDays())
            }
        }

        val savedTasks = GetSavedTasks()
        savedTasks.execute()
    }

    private fun highlightedDays(): List<EventDay> {
        val events: MutableList<EventDay> = ArrayList()
        for (i in tasks.indices) {
            val calendar = Calendar.getInstance()
            val items1 =
                tasks[i].date!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val dd = items1[0]
            val month = items1[1]
            val year = items1[2]
            calendar[Calendar.DAY_OF_MONTH] = dd.toInt()
            calendar[Calendar.MONTH] = month.toInt() - 1
            calendar[Calendar.YEAR] = year.toInt()
            events.add(EventDay(calendar, R.drawable.dot))
        }
        return events
    }
}