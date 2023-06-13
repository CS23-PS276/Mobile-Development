package com.cs23_ps276.sahabatlansia.ui.notification.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.R
import com.cs23_ps276.sahabatlansia.data.notification_database.DatabaseClient
import com.cs23_ps276.sahabatlansia.data.notification_database.model.Task
import com.cs23_ps276.sahabatlansia.databinding.ItemTaskBinding
import com.cs23_ps276.sahabatlansia.ui.notification.NotificationActivity
import com.cs23_ps276.sahabatlansia.ui.notification.botSheetFragment.CreateTaskBottomSheetFragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskAdapter(
    private val context: NotificationActivity,
    private val taskList: MutableList<Task>,
    var setRefreshListener: CreateTaskBottomSheetFragment.SetRefreshListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var dateFormat = SimpleDateFormat("EE dd MMM yyyy", Locale.US)
    private var inputDateFormat = SimpleDateFormat("dd-M-yyyy", Locale.US)
    private var date: Date? = null
    private var outputDateString: String? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(inflater, viewGroup, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

//    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        val task = taskList[position]
//        holder.title!!.text = task.taskTitle
//        holder.description!!.text = task.taskDescrption
//        holder.time!!.text = task.lastAlarm
//        holder.status!!.text = if (task.isComplete) "COMPLETED" else "UPCOMING"
//        holder.options!!.setOnClickListener { view: View? -> showPopUpMenu(view, position) }
//        try {
//            date = inputDateFormat.parse(task.date)
//            outputDateString = dateFormat.format(date)
//            val items1 = outputDateString?.split(" ".toRegex())?.dropLastWhile { it.isEmpty() }
//                ?.toTypedArray()
//            val day = items1?.getOrNull(0)
//            val dd = items1?.getOrNull(1)
//            val month = items1?.getOrNull(2)
//            holder.day!!.text = day
//            holder.date!!.text = dd
//            holder.month!!.text = month
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    private fun showPopUpMenu(view: View?, position: Int, task: Task) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.notification_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menuDelete -> {
                    val alertDialogBuilder = AlertDialog.Builder(
                        context, R.style.AppTheme_Dialog
                    )
                    alertDialogBuilder.setTitle(R.string.delete_confirmation)
                        .setMessage(R.string.sureToDelete)
                        .setPositiveButton(R.string.yes) { dialog: DialogInterface?, which: Int ->
                            deleteTaskFromId(
                                task.taskId,
                                position
                            )
                        }
                        .setNegativeButton(R.string.no) { dialog: DialogInterface, which: Int -> dialog.cancel() }
                        .show()
                }

                R.id.menuUpdate -> {
                    val createTaskBottomSheetFragment = CreateTaskBottomSheetFragment()
                    createTaskBottomSheetFragment.setTaskId(task.taskId, true, context, context)
                    createTaskBottomSheetFragment.show(
                        context.supportFragmentManager,
                        createTaskBottomSheetFragment.tag
                    )
                }

                R.id.menuComplete -> {
                    val completeAlertDialog = AlertDialog.Builder(
                        context, R.style.AppTheme_Dialog
                    )
                    completeAlertDialog.setTitle(R.string.confirmation)
                        .setMessage(R.string.sureToMarkAsComplete)
                        .setPositiveButton(R.string.yes) { dialog: DialogInterface?, which: Int ->
                            showCompleteDialog(
                                task.taskId,
                                position
                            )
                        }
                        .setNegativeButton(R.string.no) { dialog: DialogInterface, which: Int -> dialog.cancel() }
                        .show()
                }
            }
            false
        }
        popupMenu.show()
    }

    fun showCompleteDialog(taskId: Int, position: Int) {
        val dialog = Dialog(context, R.style.AppTheme)
        dialog.setContentView(R.layout.dialog_completed_theme)
        val close = dialog.findViewById<Button>(R.id.closeButton)
        close.setOnClickListener { view: View? ->
            deleteTaskFromId(taskId, position)
            dialog.dismiss()
        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun deleteTaskFromId(taskId: Int, position: Int) {
        class DeleteTaskAsyncTask : AsyncTask<Void?, Void?, List<Task>>() {
            protected override fun doInBackground(vararg voids: Void?): List<Task> {
                DatabaseClient.getInstance(context)
                    ?.appDatabase
                    ?.dataBaseAction()
                    ?.deleteTaskFromId(taskId)
                return taskList
            }

            override fun onPostExecute(tasks: List<Task>) {
                super.onPostExecute(tasks)
                removeAtPosition(position)
                setRefreshListener.refresh()
            }
        }
        val deleteTaskAsyncTask = DeleteTaskAsyncTask()
        deleteTaskAsyncTask.execute()
    }

    private fun removeAtPosition(position: Int) {
        taskList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, taskList.size)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.options.setOnClickListener(this)
        }

        fun bind(task: Task) {
            binding.title.text = task.taskTitle
            binding.description.text = task.taskDescrption
            binding.time.text = task.lastAlarm
            binding.status.text = if (task.isComplete) "COMPLETED" else "UPCOMING"

            try {
                date = inputDateFormat.parse(task.date)
                outputDateString = dateFormat.format(date)
                val items1 = outputDateString?.split(" ".toRegex())?.dropLastWhile { it.isEmpty() }
                    ?.toTypedArray()
                val day = items1?.getOrNull(0)
                val dd = items1?.getOrNull(1)
                val month = items1?.getOrNull(2)
                binding.day.text = day
                binding.date.text = dd
                binding.month.text = month
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val task = taskList[position]
                showPopUpMenu(view, position, task)
            }
        }
    }
}