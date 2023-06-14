package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs23_ps276.sahabatlansia.adapter.ListCaregiverAdapter
import com.cs23_ps276.sahabatlansia.databinding.ActivityCariCaregiverBinding

class CariCaregiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCariCaregiverBinding
    private lateinit var caregiverAdapter: ListCaregiverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCariCaregiverBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        caregiverAdapter = ListCaregiverAdapter(this)
        binding.rvCaregiver.adapter = caregiverAdapter
        binding.rvCaregiver.layoutManager = LinearLayoutManager(this)

        val caregiverIds = intent.getStringArrayListExtra("caregiverId")
        if (caregiverIds != null) {
            showRecommendedCaregivers(caregiverIds)
        }

        binding.btnCariCaregiver.setOnClickListener {
            val intent = Intent(this, RekomendationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showRecommendedCaregivers(caregiverIds: ArrayList<String>) {
        val caregivers = caregiverIds.map { "ID Caregiver: $it - Recommended Caregiver" }
        caregiverAdapter.setCaregiver(caregivers)
    }
}

