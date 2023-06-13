package com.cs23_ps276.sahabatlansia.ui.notification.broadcastReceiver


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.cs23_ps276.sahabatlansia.ui.notification.AlarmActivity

class AlarmBroadcastReceiver : BroadcastReceiver() {
    var title: String? = null
    var desc: String? = null
    var date: String? = null
    var time: String? = null
    override fun onReceive(context: Context, intent: Intent) {
        title = intent.getStringExtra("TITLE")
        desc = intent.getStringExtra("DESC")
        date = intent.getStringExtra("DATE")
        time = intent.getStringExtra("TIME")

        val i = Intent(context, AlarmActivity::class.java)
        i.putExtra("TITLE", title)
        i.putExtra("DESC", desc)
        i.putExtra("DATE", date)
        i.putExtra("TIME", time)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(i)
    }
}