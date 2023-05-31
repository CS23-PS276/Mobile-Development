package com.cs23_ps276.sahabatlansia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.adapter.ListCaregiverAdapter
import com.cs23_ps276.sahabatlansia.adapter.ListHistoryAdapter
import com.cs23_ps276.sahabatlansia.data.Caregiver
import com.cs23_ps276.sahabatlansia.data.History

class HistoryActivity : AppCompatActivity() {
    private lateinit var rvHistory: RecyclerView
    private val list = ArrayList<History>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        rvHistory = findViewById(R.id.rv_history)
        rvHistory.setHasFixedSize(true)
        list.addAll(getListHistory())
        showRecyclerList()
    }

    private fun getListHistory(): ArrayList<History> {
        val dataName = resources.getStringArray(R.array.data_nama)
        val dataAsal = resources.getStringArray(R.array.data_asal)
        val dataWaktu = resources.getStringArray(R.array.data_waktu)
        val listHistory = ArrayList<History>()
        for (i in dataName.indices) {
            val history = History(dataWaktu[i],dataName[i], dataAsal[i])
            listHistory.add(history)
        }
        return listHistory
    }

    private fun showRecyclerList() {
        rvHistory.layoutManager = LinearLayoutManager(this)
        val listHistoryAdapter = ListHistoryAdapter(list)
        rvHistory.adapter = listHistoryAdapter
    }
}