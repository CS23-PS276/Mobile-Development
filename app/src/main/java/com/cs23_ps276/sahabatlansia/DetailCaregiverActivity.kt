package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.net.URLEncoder

class DetailCaregiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_caregiver)

        val button = findViewById<Button>(R.id.btn_pesan)

        // Get caregiver name from intent
        val caregiverName = intent.getStringExtra("caregiverName")

        button.setOnClickListener {
            whatsApp("+62", "85831000308")
        }
    }
    private fun whatsApp(code: String, phoneNumber: String) {
        try {
            val packageManager = this.packageManager
            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=$code$phoneNumber&text=${URLEncoder.encode("Hello I am KB Coder", "UTF-8")}"
            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(url)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Anda belum punya Whatsapp", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "ERROR: ${e.stackTrace}", Toast.LENGTH_SHORT).show()
        }
    }
}