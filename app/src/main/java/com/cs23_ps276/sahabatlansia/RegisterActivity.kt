package com.cs23_ps276.sahabatlansia

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.cs23_ps276.sahabatlansia.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerButton.setOnClickListener {
            if (isEmailValid() && isPasswordValid()) {
                // Start LoginActivity
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.haveAccount.setOnClickListener {
            // Start LoginActivity
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isEmailValid(): Boolean {
        val email = binding.edEmail.text.toString().trim()

        return if (email.isEmpty()) {
            binding.edEmail.error = "Email is required"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edEmail.error = "Invalid email address"
            false
        } else {
            binding.edEmail.error = null
            true
        }
    }

    private fun isPasswordValid(): Boolean {
        val password = binding.edPassword.text.toString().trim()

        return if (password.isEmpty()) {
            binding.edPassword.error = "Password is required"
            false
        } else if (password.length < 5) {
            binding.edPassword.error = "Password should be at least 5 characters"
            false
        } else {
            binding.edPassword.error = null
            true
        }
    }
}



