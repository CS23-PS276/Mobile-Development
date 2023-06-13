package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs23_ps276.sahabatlansia.adapter.ListCaregiverAdapter
import com.cs23_ps276.sahabatlansia.data.Caregiver
import com.cs23_ps276.sahabatlansia.databinding.ActivityCariCaregiverBinding

class CariCaregiverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCariCaregiverBinding
    private val list = ArrayList<Caregiver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCariCaregiverBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCariCaregiver.setOnClickListener {
            val intent = Intent(this, RekomendationActivity::class.java)
            startActivity(intent)
        }
        binding.rvCaregiver.setHasFixedSize(true)

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
        dataPhoto.recycle()
        return listCaregiver
    }

    private fun showRecyclerList() {
        binding.rvCaregiver.layoutManager = LinearLayoutManager(this)
        val listCaregiverAdapter = ListCaregiverAdapter(list)
        binding.rvCaregiver.adapter = listCaregiverAdapter
    }
}