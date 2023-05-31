package com.cs23_ps276.sahabatlansia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs23_ps276.sahabatlansia.adapter.ListCaregiverAdapter
import com.cs23_ps276.sahabatlansia.data.Caregiver

class CariCaregiverActivity : AppCompatActivity() {
    private lateinit var rvCaregiver: RecyclerView
    private val list = ArrayList<Caregiver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_caregiver)

        rvCaregiver = findViewById(R.id.rv_caregiver)
        rvCaregiver.setHasFixedSize(true)

        list.addAll(getListCaregiver())
        showRecyclerList()
    }

    private fun getListCaregiver(): ArrayList<Caregiver> {
        val dataName = resources.getStringArray(R.array.data_nama)
        val dataDescription = resources.getStringArray(R.array.data_asal)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCaregiver = ArrayList<Caregiver>()
        for (i in dataName.indices) {
            val caregiver = Caregiver(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listCaregiver.add(caregiver)
        }
        return listCaregiver
    }

    private fun showRecyclerList() {
        rvCaregiver.layoutManager = LinearLayoutManager(this)
        val listCaregiverAdapter = ListCaregiverAdapter(list)
        rvCaregiver.adapter = listCaregiverAdapter
    }
}