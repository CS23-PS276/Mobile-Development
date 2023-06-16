package com.cs23_ps276.sahabatlansia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LansiaFormActivity : AppCompatActivity() {
    private lateinit var etNamaPasien: EditText
    private lateinit var etTinggi: EditText
    private lateinit var etWeight: EditText
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lansia_form)

        etNamaPasien = findViewById(R.id.et_nama_pasien)
        etTinggi = findViewById(R.id.et_tinggi)
        etWeight = findViewById(R.id.et_weight)
        btnAdd = findViewById(R.id.btn_add)

        btnAdd.setOnClickListener {
            val namaPasien = etNamaPasien.text.toString()
            val tinggi = etTinggi.text.toString()
            val weight = etWeight.text.toString()

            Toast.makeText(this, "Data Successfully Added", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomepageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}